package gov.nysed.dockertutorial.repository;

import gov.nysed.dockertutorial.dto.ProjectDto;
import gov.nysed.dockertutorial.model.Project;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectRepository extends JpaRepository<Project, Long> {
  Optional<Project> findByName(String name);
  @Query("SELECT new gov.nysed.dockertutorial.dto.ProjectDto(" +
      "m.id, " +
      "m.name) " +
      "FROM Project m " +
      "ORDER BY LOWER(m.name) ASC")
  List<ProjectDto> findAllDto();
}
