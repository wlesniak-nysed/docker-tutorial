package gov.nysed.dockertutorial.dto;

import gov.nysed.dockertutorial.enums.ProjectRole;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ProjectMembershipDto {

  Long userId;
  String userName;
  Long projectId;
  String projectName;
  ProjectRole role;

}
