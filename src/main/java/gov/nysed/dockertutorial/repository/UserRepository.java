package gov.nysed.dockertutorial.repository;

import gov.nysed.dockertutorial.model.User;
import gov.nysed.dockertutorial.projection.UserProjection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserName(String name);
  @Query(value = "SELECT u.id AS id, " +
      "       u.user_name AS userName, " +
      "       u.first_name AS firstName, " +
      "       u.last_name AS lastName, " +
      "       CAST(COALESCE(REGEXP_SUBSTR(u.user_name, '\\d+$'), '0') AS INT) AS userNameSuffix " +
      "FROM users u " +
      "WHERE u.user_name LIKE CONCAT(:userName, '%') " + // Matched param name
      "ORDER BY userNameSuffix DESC " +
      "LIMIT 1",
      nativeQuery = true)
  Optional<UserProjection> findHighestUserNumericByUsernamePrefix(@Param("userName") String userName);
  // return custom sort that will give aavery19 after aavery2
  @Query(value = "SELECT u.id AS id, " +
      "       u.user_name AS userName, " +
      "       u.first_name AS firstName, " +
      "       u.last_name AS lastName " +
      "FROM users u " +
      "ORDER BY REGEXP_REPLACE(u.user_name, '\\d+$', '') ASC, " +
      "         CAST(COALESCE(REGEXP_SUBSTR(u.user_name, '\\d+$'), '0') AS INT) ASC",
      nativeQuery = true)
  List<UserProjection> findAllOrderedNumericallyNative();
}