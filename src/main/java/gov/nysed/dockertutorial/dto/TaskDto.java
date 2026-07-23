package gov.nysed.dockertutorial.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class TaskDto {
  Long id;
  String title;
  String description;
  Long projectId;
  String projectName;
  Long parentTaskId;
  List<TaskDto> subTasks;

  // JsonCreator/JsonProperty annotations fix issues with Jackson deserialization when using
  // Lombok's @Value annotation- in a perfect world you wouldn't need a custom constructor for a DTO
  @JsonCreator
  public TaskDto(
      @JsonProperty("id") Long id,
      @JsonProperty("title") String title,
      @JsonProperty("description") String description,
      @JsonProperty("projectId") Long projectId,
      @JsonProperty("projectName") String projectName,
      @JsonProperty("parentTaskId") Long parentTaskId) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.projectId = projectId;
    this.projectName = projectName;
    this.parentTaskId = parentTaskId;
    this.subTasks = new ArrayList<>();
  }
}
