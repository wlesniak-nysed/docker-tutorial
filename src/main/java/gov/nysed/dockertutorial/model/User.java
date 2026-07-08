package gov.nysed.dockertutorial.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  // membershipId structure means we need unique names for users and projects
  @Column(name = "name", unique = true, nullable = false)
  String name;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProjectMembership> projectMemberships = new ArrayList<>();

  public User() { }

  public User(String name) {
    this.name = name;
  }

  public User(String name, List<ProjectMembership> projectMemberships) {
    this.name = name;
    this.projectMemberships = projectMemberships;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public List<ProjectMembership> getProjectMemberships() { return projectMemberships; }
  public void setProjectMemberships(List<ProjectMembership> projectMemberships) {
    this.projectMemberships = projectMemberships;
  }

}