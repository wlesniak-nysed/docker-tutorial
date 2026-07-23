package gov.nysed.dockertutorial.enums;

public enum ProjectRole {
  ADMIN("Administrator"),
  DEVELOPER("Developer"),
  VIEWER("Viewer");

  private final String displayName;

  ProjectRole(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return displayName;
  }
}
