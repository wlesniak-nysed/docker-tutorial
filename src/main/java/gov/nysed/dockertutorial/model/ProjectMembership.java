package gov.nysed.dockertutorial.model;

import gov.nysed.dockertutorial.enums.ProjectRole;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "project_memberships")
public class ProjectMembership {

  @EmbeddedId
  private ProjectMembershipId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("projectId")
  @JoinColumn(name = "project_id")
  private Project project;

  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  private ProjectRole role; // E.g., ADMIN, VIEWER, DEVELOPER

  // Constructor, Getters, and Setters
  public ProjectMembership() {}

  public ProjectMembership(User user, Project project, ProjectRole role) {
    this.user = user;
    this.project = project;
    this.role = role;
    this.id = new ProjectMembershipId(user.getId(), project.getId());
  }
}
