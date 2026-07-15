package gov.nysed.dockertutorial.projection;

// when returning from a native query we need a projection to map the DTO to
// there's a possible alternate solution with jdbcTemplate mapping but it clutters our
// UserDto object while also requiring us to create a custom user repository
// which seems like a lot of extra code for no benefit
public interface UserProjection {
  Long getId();
  String getUserName();
  String getFirstName();
  String getLastName();
  // captures extracted suffix->"1" from usernames so we can properly increment
  Integer getUserNameSuffix();
}
