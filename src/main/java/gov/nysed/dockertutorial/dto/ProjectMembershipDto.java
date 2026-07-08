package gov.nysed.dockertutorial.dto;

import gov.nysed.dockertutorial.enums.ProjectRole;

public class ProjectMembershipDto {

  Long userId;
  String userName;
  Long projectId;
  String projectName;
  ProjectRole role;

  public ProjectMembershipDto() { }

  public ProjectMembershipDto(Long userId, String userName, Long projectId, String projectName,
      ProjectRole role) {
    this.userId = userId;
    this.userName = userName;
    this.projectId = projectId;
    this.projectName = projectName;
    this.role = role;
  }

  public Long getUserId() { return userId; }
  public String getUserName() { return userName; }
  public Long getProjectId() { return projectId; }
  public String getProjectName() { return projectName; }
  public ProjectRole getRole() { return role; }

}
