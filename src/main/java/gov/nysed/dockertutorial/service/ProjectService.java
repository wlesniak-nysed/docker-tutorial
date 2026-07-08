package gov.nysed.dockertutorial.service;

import gov.nysed.dockertutorial.enums.ProjectRole;
import gov.nysed.dockertutorial.model.Project;
import gov.nysed.dockertutorial.model.ProjectMembership;
import gov.nysed.dockertutorial.model.User;
import gov.nysed.dockertutorial.repository.ProjectMembershipRepository;
import gov.nysed.dockertutorial.repository.ProjectRepository;
import gov.nysed.dockertutorial.repository.UserRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {

  private final UserRepository userRepository;
  private final ProjectRepository projectRepository;
  private final ProjectMembershipRepository membershipRepository;

  // Standard constructor injection
  public ProjectService(UserRepository userRepository,
      ProjectRepository projectRepository,
      ProjectMembershipRepository membershipRepository) {
    this.userRepository = userRepository;
    this.projectRepository = projectRepository;
    this.membershipRepository = membershipRepository;
  }

  @Transactional
  public void assignUserToProject(String userName, String projectName, ProjectRole role) {
    // 1. Fetch and verify the User exists
    User user = userRepository.findByName(userName)
                              .orElseThrow(() -> new EntityNotFoundException("User not found with name: " + userName));

    // 2. Fetch and verify the Project exists
    Project project = projectRepository.findByName(projectName)
                                       .orElseThrow(() -> new EntityNotFoundException("Project not found with name: " + projectName));

    // 3. Create the Membership instance
    // The constructor handles setting up the ProjectMembershipId internally using user.getId() and project.getId()
    ProjectMembership membership = new ProjectMembership(user, project, role);

    // 4. Save the relationship
    membershipRepository.save(membership);
  }
}