package gov.nysed.dockertutorial.service;

import gov.nysed.dockertutorial.dto.TaskDto;
import gov.nysed.dockertutorial.model.Project;
import gov.nysed.dockertutorial.model.Task;
import gov.nysed.dockertutorial.repository.ProjectRepository;
import gov.nysed.dockertutorial.repository.TaskRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

  private final TaskRepository taskRepository;
  private final ProjectRepository projectRepository;

  public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
    this.taskRepository = taskRepository;
    this.projectRepository = projectRepository;
  }

  @Transactional(readOnly = true)
  public List<TaskDto> getAllTaskTrees() {
    List<TaskDto> rootDtos = taskRepository.findAllRootTaskDtos();
    return rootDtos.stream()
                   .map(this::hydrateSubTasks)
                   .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<TaskDto> getProjectTaskTree(Long projectId) {
    List<TaskDto> rootDtos = taskRepository.findRootTaskDtosByProjectId(projectId);
    // hydrate the subtask tree using a helper mapping method
    return rootDtos.stream()
                   .map(this::hydrateSubTasks)
                   .collect(Collectors.toList());
  }

  private TaskDto hydrateSubTasks(TaskDto dto) {
    // fetch the managed entity just to grab its initialized children seamlessly
    Task taskEntity = taskRepository.findById(dto.getId())
                                    .orElseThrow(() -> new RuntimeException("Task not found"));

    // recursively convert child Entity tasks to child TaskDtos
    List<TaskDto> childDtos = taskEntity.getSubTasks().stream()
                                        .map(childEntity -> new TaskDto(
                                            childEntity.getId(),
                                            childEntity.getTitle(),
                                            childEntity.getDescription(),
                                            childEntity.getProject().getId(),
                                            childEntity.getProject().getName(),
                                            childEntity.getParentTask().getId(),
                                            new ArrayList<>()// Temporary empty list for deep children
                                        ))
                                        // Deep recursive call to catch sub-subtasks if they exist
                                        .map(this::hydrateSubTasks)
                                        .collect(Collectors.toList());

    return new TaskDto(
        dto.getId(),
        dto.getTitle(),
        dto.getDescription(),
        dto.getProjectId(),
        dto.getProjectName(),
        dto.getParentTaskId(),
        childDtos
    );
  }

  public TaskDto createTask(TaskDto taskDto) {
    Project project = projectRepository.findById(taskDto.getProjectId())
                                       .orElseThrow(() -> new RuntimeException("Project not found"));
    Task newTask = new Task(taskDto.getTitle(), taskDto.getDescription(), project);
    if (taskDto.getParentTaskId() != null && taskDto.getParentTaskId() != -1) {
      Task parentTask = taskRepository.findById(taskDto.getParentTaskId())
                                      .orElseThrow(() -> new RuntimeException("Parent task not found"));
      parentTask.addSubTask(newTask);
      // Saving parent automatically saves newTask via CascadeType.ALL
      parentTask = taskRepository.save(parentTask);
      // Find our newly persisted child inside the saved parent to grab its generated ID
      Task childTask = parentTask.getSubTasks().get(parentTask.getSubTasks().size() - 1);
      return mapToDto(childTask);
    } else {
      Task savedTask = taskRepository.save(newTask);
      return mapToDto(savedTask);
    }
  }

  private TaskDto mapToDto(Task task) {
    return new TaskDto(
        task.getId(),
        task.getTitle(),
        task.getDescription(),
        task.getProject().getId(),
        task.getProject().getName(),
        task.getParentTask() != null ? task.getParentTask().getId() : null
    );
  }
}