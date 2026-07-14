package gov.nysed.dockertutorial.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  // membershipId structure means we need unique names for users and projects
  @Column(name = "userName", unique = true, nullable = false)
  String userName;

  @Column(name = "firstName", nullable = false)
  String firstName;

  @Column(name = "lastName", nullable = false)
  String lastName;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProjectMembership> projectMemberships = new ArrayList<>();

  public User(String userName, String firstName, String lastName) {
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
  }

}