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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

  @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Task> tasks = new ArrayList<>();

  // Helper methods to keep relationships in sync
  public void addTask(Task task) {
    tasks.add(task);
    task.setProject(this);
  }

  public void removeTask(Task task) {
    tasks.remove(task);
    task.setProject(null);
  }
}
