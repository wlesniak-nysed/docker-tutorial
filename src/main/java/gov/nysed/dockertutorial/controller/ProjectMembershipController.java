package gov.nysed.dockertutorial.controller;

import gov.nysed.dockertutorial.dto.ProjectMembershipDto;
import gov.nysed.dockertutorial.service.ProjectMembershipService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
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





}
