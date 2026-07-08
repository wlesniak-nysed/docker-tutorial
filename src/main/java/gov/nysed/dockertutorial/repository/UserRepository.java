package gov.nysed.dockertutorial.repository;


import gov.nysed.dockertutorial.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  long deleteByName(String name);
  Optional<User> findByName(String name);
}