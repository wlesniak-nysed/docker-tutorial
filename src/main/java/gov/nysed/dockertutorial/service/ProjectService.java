package gov.nysed.dockertutorial.service;

import gov.nysed.dockertutorial.dto.ProjectDto;
import gov.nysed.dockertutorial.model.Project;
import gov.nysed.dockertutorial.repository.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {

  private final ProjectRepository projectRepository;

  @Autowired
  public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  public List<ProjectDto> getAllProjects() {
    return projectRepository.findAllDto();
  }

  @Transactional
  public Project createProject(ProjectDto projectDto) {
    Project project = new Project(projectDto.getName());
    return projectRepository.save(project);
  }

  @Transactional
  public ResponseEntity<Object> deleteProject(ProjectDto projectDto) {
    // deleteById doesn't return a count when the entity is not found, but it will throw an
    // exception; so catch it and return an appropriate response
    try {
      projectRepository.deleteById(projectDto.getId());
      return ResponseEntity.ok().build();
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.notFound().build();
    }
  }
  

}