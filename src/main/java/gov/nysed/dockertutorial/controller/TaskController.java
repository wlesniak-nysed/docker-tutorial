package gov.nysed.dockertutorial.controller;

import gov.nysed.dockertutorial.dto.TaskDto;
import gov.nysed.dockertutorial.service.TaskService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/tasks")
public class TaskController {

  private final TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @GetMapping("/all")
  public List<TaskDto> getAllTasks() {
    return taskService.getAllTaskTrees();
  }

  @PostMapping("/create")
  public TaskDto createTask(@RequestBody TaskDto taskDto) {
    return taskService.createTask(taskDto);
  }
}
