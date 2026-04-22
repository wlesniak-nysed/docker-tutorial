# Stage 1: Build the Vue frontend
FROM node:20-alpine AS frontend-build
WORKDIR /frontend
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ ./
RUN npm run build

# Stage 2: Resolve Gradle dependencies
FROM eclipse-temurin:8-jdk-jammy AS deps
WORKDIR /build
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle settings.gradle ./
# Pre-download dependencies to cache this layer
RUN ./gradlew dependencies --no-daemon

# Stage 3: Build the Spring Boot application
FROM deps AS package
WORKDIR /build
# Copy the compiled Vue files from Stage 1 into Spring's static resources
COPY --from=frontend-build /frontend/dist ./src/main/resources/static
COPY src/ ./src/
# buildVue/copyVueToSpring steps are being skipped since they're already built in step 1;
# (why not remove these steps then?) they're left in the build scripts for local builds
RUN ./gradlew bootJar -x buildVue -x copyVueToSpring --no-daemon
RUN cp build/libs/*.jar app.jar

# Stage 4: Extract layers for optimized Docker layers
FROM package AS extract
WORKDIR /build
RUN java -Djarmode=layertools -jar app.jar extract --destination extracted

# Stage 5: Final runtime image
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
