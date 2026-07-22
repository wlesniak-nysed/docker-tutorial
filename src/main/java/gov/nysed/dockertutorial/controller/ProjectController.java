package gov.nysed.dockertutorial.controller;

import gov.nysed.dockertutorial.dto.ProjectDto;
import gov.nysed.dockertutorial.model.Project;
import gov.nysed.dockertutorial.service.ProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/projects")
public class ProjectController {

  private final ProjectService projectService;

  @Autowired
  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @GetMapping("/all")
  public List<ProjectDto> getAllProjects() {
    return projectService.getAllProjects();
  }

  @PostMapping("/create")
  public ProjectDto createProject(@RequestBody ProjectDto projectDto) {
    Project newProject = projectService.createProject(projectDto);
    return new ProjectDto(newProject.getId(), newProject.getName());
  }

  @DeleteMapping("/delete")
  public ResponseEntity<Object> deleteProject(@RequestBody ProjectDto projectDto) {
    return projectService.deleteProject(projectDto);
  }

}
