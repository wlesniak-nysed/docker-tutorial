package gov.nysed.dockertutorial.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id", nullable = false)
  @JsonIgnoreProperties("tasks") // Prevents infinite serialization loops
  private Project project;

  // Self-referential relationship for parent task
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_task_id")
  @JsonIgnoreProperties("subTasks")
  private Task parentTask;

  // Self-referential relationship for child sub-tasks
  @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Task> subTasks = new ArrayList<>();

  public Task(String title, String description, Project project) {
    this.title = title;
    this.description = description;
    this.project = project;
  }

  // Helper methods to maintain bidirectional sync
  public void addSubTask(Task subTask) {
    subTasks.add(subTask);
    subTask.setParentTask(this);
    subTask.setProject(this.project); // Sub-tasks belong to the same project
  }

  public void removeSubTask(Task subTask) {
    subTasks.remove(subTask);
    subTask.setParentTask(null);
  }
}