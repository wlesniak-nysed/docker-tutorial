package gov.nysed.dockertutorial.repository;

import gov.nysed.dockertutorial.dto.TaskDto;
import gov.nysed.dockertutorial.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
  @Query("SELECT new gov.nysed.dockertutorial.dto.TaskDto(" +
      "t.id, t.title, t.description, t.project.id, t.project.name, t.parentTask.id) " +
      "FROM Task t " +
      "WHERE t.parentTask IS NULL")
  List<TaskDto> findAllRootTaskDtos();
  @Query("SELECT new gov.nysed.dockertutorial.dto.TaskDto(" +
      "t.id, t.title, t.description, t.project.id, t.project.name, t.parentTask.id) " +
      "FROM Task t")
  List<TaskDto> findAllTaskDtos();
  @Query("SELECT new gov.nysed.dockertutorial.dto.TaskDto(" +
      "t.id, t.title, t.description, t.project.id, t.project.name, t.parentTask.id) " +
      "FROM Task t WHERE t.project.id = :projectId AND t.parentTask IS NULL")
  List<TaskDto> findRootTaskDtosByProjectId(@Param("projectId") Long projectId);
}