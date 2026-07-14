package gov.nysed.dockertutorial.repository;


import gov.nysed.dockertutorial.dto.UserDto;
import gov.nysed.dockertutorial.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserName(String name);
  @Query("SELECT new gov.nysed.dockertutorial.dto.UserDto(" +
      "m.id, " +
      "m.userName, " +
      "m.firstName, " +
      "m.lastName) " +
      "FROM User m " +
      "ORDER BY LOWER(m.userName) ASC")
  List<UserDto> findAllDto();
}