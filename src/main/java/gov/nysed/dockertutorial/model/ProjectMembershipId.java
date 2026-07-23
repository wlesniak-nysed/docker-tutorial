package gov.nysed.dockertutorial.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectMembershipId implements Serializable {

  private Long userId;
  private Long projectId;

  public ProjectMembershipId() {}

  public ProjectMembershipId(Long userId, Long projectId) {
    this.userId = userId;
    this.projectId = projectId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ProjectMembershipId that = (ProjectMembershipId) o;
    return Objects.equals(userId, that.userId) && Objects.equals(projectId, that.projectId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, projectId);
  }
}

