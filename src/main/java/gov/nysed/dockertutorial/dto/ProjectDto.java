package gov.nysed.dockertutorial.dto;

public class ProjectDto {

  Long id;
  String name;

  public ProjectDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }
  public ProjectDto(String name) {
    this.name = name;
  }
  public ProjectDto() { }

  public Long getId() { return id; }
  public String getName() { return name; }
}
