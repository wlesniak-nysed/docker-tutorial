package gov.nysed.dockertutorial.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  // membershipId structure means we need unique names for users and projects
  @Column(name = "name", unique = true, nullable = false)
  String name;

  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProjectMembership> projectMemberships = new ArrayList<>();

  public Project(String name) {
    this.name = name;
  }

  public Project(String name, List<ProjectMembership> projectMemberships) {
    this.name = name;
    this.projectMemberships = projectMemberships;
  }

  public Project() { }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public List<ProjectMembership> getProjectMemberships() { return projectMemberships; }
  public void setProjectMemberships(List<ProjectMembership> projectMemberships) {
    this.projectMemberships = projectMemberships;
  }


}
