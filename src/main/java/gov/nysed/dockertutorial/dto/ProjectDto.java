package gov.nysed.dockertutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ProjectDto {

  Long id;
  String name;

}
