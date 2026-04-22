# Stage 0: Base setup - copy gradle settings/wrapper/build file
FROM eclipse-temurin:8-jdk-jammy AS base
WORKDIR /build
COPY --chmod=0755 gradlew gradlew
COPY gradle/ gradle/
COPY build.gradle settings.gradle ./

# Stage 1: Resolve & Cache Dependencies
FROM base AS deps
# Use a mount cache so the .gradle folder persists across builds
RUN --mount=type=cache,target=/root/.gradle ./gradlew dependencies --no-daemon

# Stage 2: Run the tests (starts from deps to use cached jars)
FROM deps AS test
COPY ./src src/
# we're skipping the buildVue/copyVueToSpring steps since they're not needed for testing
RUN --mount=type=cache,target=/root/.gradle ./gradlew test -x buildVue -x copyVueToSpring --no-daemon

# Stage 3: Build the Vue frontend
FROM node:20-alpine AS frontend-build
WORKDIR /frontend
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ ./
RUN npm run build

# Stage 4: Build the Spring Boot application
FROM deps AS package
WORKDIR /build
# Copy the compiled Vue files from Stage 1 into Spring's static resources
COPY --from=frontend-build /frontend/dist ./src/main/resources/static
COPY src/ ./src/
# buildVue/copyVueToSpring steps are being skipped since they're already built in step 1;
# (why not remove these steps then?) they're left in the build scripts for local builds
RUN ./gradlew bootJar -x buildVue -x copyVueToSpring --no-daemon
RUN cp build/libs/*.jar app.jar

# Stage 5: Extract layers for optimized Docker layers
FROM package AS extract
WORKDIR /build
RUN java -Djarmode=layertools -jar app.jar extract --destination extracted

# Stage 6: The "Debugger" version
FROM extract AS development
WORKDIR /build
RUN cp -r /build/extracted/dependencies/. ./
RUN cp -r /build/extracted/spring-boot-loader/. ./
RUN cp -r /build/extracted/snapshot-dependencies/. ./
RUN cp -r /build/extracted/application/. ./
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000
CMD ["java", "org.springframework.boot.loader.JarLauncher"]

# Stage 7: Final runtime image
FROM eclipse-temurin:8-jre-jammy AS final
WORKDIR /app

# Create a non-root user for security
RUN adduser --disabled-password --gecos "" --home "/nonexistent" --shell "/sbin/nologin" --no-create-home --uid 10001 appuser

# Copy extracted layers from the extract stage
COPY --from=extract /build/extracted/dependencies/ ./
COPY --from=extract /build/extracted/spring-boot-loader/ ./
COPY --from=extract /build/extracted/snapshot-dependencies/ ./
COPY --from=extract /build/extracted/application/ ./

# Create directory for H2 file persistence and set permissions
RUN mkdir /data && chown appuser:appuser /data
VOLUME /data

USER appuser
EXPOSE 8080

# Spring Boot 2.7.x uses this specific Launcher path
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
