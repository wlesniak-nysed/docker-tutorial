package gov.nysed.dockertutorial.service;

import gov.nysed.dockertutorial.dto.ProjectMembershipDto;
import gov.nysed.dockertutorial.enums.ProjectRole;
import gov.nysed.dockertutorial.model.Project;
import gov.nysed.dockertutorial.model.ProjectMembership;
import gov.nysed.dockertutorial.model.User;
import gov.nysed.dockertutorial.repository.ProjectMembershipRepository;
import gov.nysed.dockertutorial.repository.ProjectRepository;
import gov.nysed.dockertutorial.repository.UserRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectMembershipService {

  private final ProjectMembershipRepository projectMembershipRepository;
  private final ProjectRepository projectRepository;
  private final UserRepository userRepository;

  @Autowired
  public ProjectMembershipService(ProjectMembershipRepository projectMembershipRepository,
      ProjectRepository projectRepository, UserRepository userRepository) {
    this.projectMembershipRepository = projectMembershipRepository;
    this.projectRepository = projectRepository;
    this.userRepository = userRepository;
  }

  public List<ProjectMembershipDto> findAllDto() {
    return projectMembershipRepository.findAllDto();
  }

  @Transactional
  public void assignUserToProject(String userName, String projectName, ProjectRole role) {
    // Fetch and verify the User exists
    User user = userRepository.findByUserName(userName)
                              .orElseThrow(() -> new EntityNotFoundException(
                                  "User not found with name: " + userName));

    // Fetch and verify the Project exists
    Project project = projectRepository.findByName(projectName)
                                       .orElseThrow(() -> new EntityNotFoundException(
                                           "Project not found with name: " + projectName));

    // Create the Membership instance and save it
    // The constructor handles setting up the ProjectMembershipId internally using user.getId() and project.getId()
    ProjectMembership membership = new ProjectMembership(user, project, role);
    projectMembershipRepository.save(membership);
  }
}
