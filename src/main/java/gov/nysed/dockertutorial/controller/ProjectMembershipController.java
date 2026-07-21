package gov.nysed.dockertutorial.controller;

import gov.nysed.dockertutorial.dto.ProjectDto;
import gov.nysed.dockertutorial.dto.ProjectMembershipAssignmentRequest;
import gov.nysed.dockertutorial.dto.ProjectMembershipDto;
import gov.nysed.dockertutorial.service.ProjectMembershipService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/project-membership")
public class ProjectMembershipController {

  private final ProjectMembershipService projectMembershipService;

  public ProjectMembershipController(ProjectMembershipService projectMembershipService) {
    this.projectMembershipService = projectMembershipService;
  }

  @GetMapping("/all")
  public List<ProjectMembershipDto> getAllProjectMemberships() {
    return projectMembershipService.findAllDto();
  }

  @PostMapping("/create")
  public void createProjectMembership(@RequestBody
      ProjectMembershipAssignmentRequest projectMembershipAssignmentRequest) {
    projectMembershipService.assignUserToProject(
        projectMembershipAssignmentRequest.getUserDto().getUserName(),
        projectMembershipAssignmentRequest.getProjectDto().getName(),
        projectMembershipAssignmentRequest.getProjectRole());
  }

  @DeleteMapping("/delete")
  public ResponseEntity<Object> deleteProjectMembership(@RequestBody
      ProjectMembershipDto projectMembershipDto) {
    return projectMembershipService.deleteProjectMembership(projectMembershipDto.getUserId(),
        projectMembershipDto.getProjectId());
  }

}
