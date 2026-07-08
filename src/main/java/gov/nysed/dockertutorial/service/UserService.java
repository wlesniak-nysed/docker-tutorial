package gov.nysed.dockertutorial.service;

import gov.nysed.dockertutorial.dto.UserDto;
import gov.nysed.dockertutorial.model.User;
import gov.nysed.dockertutorial.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserDto> getAllUsers() {
    return userRepository.findAllDto();
  }

  @Transactional
  public User createUser(UserDto userDto) {
    User user = new User(userDto.getName());
    return userRepository.save(user);
  }

  @Transactional
  public ResponseEntity<Object> deleteUser(UserDto userDto) {
    // deleteById doesn't return a count when the entity is not found, but it will throw an
    // exception; so catch it and return an appropriate response
    try {
      userRepository.deleteById(userDto.getId());
      return ResponseEntity.ok().build();
    } catch (EmptyResultDataAccessException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
