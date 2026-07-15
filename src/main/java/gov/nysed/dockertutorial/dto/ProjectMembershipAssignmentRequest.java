package gov.nysed.dockertutorial.dto;

import gov.nysed.dockertutorial.enums.ProjectRole;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ProjectMembershipAssignmentRequest {

  UserDto userDto;
  ProjectDto projectDto;
  ProjectRole projectRole;

}
