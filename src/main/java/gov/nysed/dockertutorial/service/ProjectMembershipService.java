package gov.nysed.dockertutorial.service;

import gov.nysed.dockertutorial.dto.ProjectMembershipDto;
import gov.nysed.dockertutorial.repository.ProjectMembershipRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectMembershipService {

  private final ProjectMembershipRepository projectMembershipRepository;

  @Autowired
  public ProjectMembershipService(ProjectMembershipRepository projectMembershipRepository) {
    this.projectMembershipRepository = projectMembershipRepository;
  }

  public List<ProjectMembershipDto> findAllDto() {
    return projectMembershipRepository.findAllDto();
  }
}
