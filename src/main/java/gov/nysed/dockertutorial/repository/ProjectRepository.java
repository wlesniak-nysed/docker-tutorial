package gov.nysed.dockertutorial.repository;

import gov.nysed.dockertutorial.model.Project;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
  Optional<Project> findByName(String name);
}
