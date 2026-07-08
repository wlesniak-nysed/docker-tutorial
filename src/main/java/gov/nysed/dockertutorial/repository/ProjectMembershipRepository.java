package gov.nysed.dockertutorial.repository;

import gov.nysed.dockertutorial.model.ProjectMembership;
import gov.nysed.dockertutorial.model.ProjectMembershipId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMembershipRepository extends
    JpaRepository<ProjectMembership, ProjectMembershipId> {
}