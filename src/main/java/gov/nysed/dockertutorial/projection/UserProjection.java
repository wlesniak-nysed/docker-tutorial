package gov.nysed.dockertutorial.projection;

// when returning from a native query we need a projection to map the DTO to
// there's an alternate solution with jdbcTemplate mapping but
public interface UserProjection {
  Long getId();
  String getUserName();
  String getFirstName();
  String getLastName();
  // captures extracted suffix->"1" from usernames so we can properly increment
  Integer getUserNameSuffix();
}
