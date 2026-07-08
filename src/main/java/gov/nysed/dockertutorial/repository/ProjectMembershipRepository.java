package gov.nysed.dockertutorial.repository;

import gov.nysed.dockertutorial.dto.ProjectMembershipDto;
import gov.nysed.dockertutorial.model.ProjectMembership;
import gov.nysed.dockertutorial.model.ProjectMembershipId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectMembershipRepository extends
    JpaRepository<ProjectMembership, ProjectMembershipId> {
  @Query("SELECT new gov.nysed.dockertutorial.dto.ProjectMembershipDto(" +
      "m.user.id, " +
      "m.user.name, " +
      "m.project.id, " +
      "m.project.name, " +
      "m.role) " +
      "FROM ProjectMembership m")
  List<ProjectMembershipDto> findAllDto();
}