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
    return userRepository.findAllOrderedNumericallyNative().stream()
                         .map(p -> new UserDto(p.getId(), p.getUserName(), p.getFirstName(), p.getLastName()))
                         .collect(Collectors.toList());
  }

  public String returnValidUserName(String userName) {
    return userRepository.findHighestUserNumericByUsernamePrefix(userName)
                         .map(projection -> {
                           int nextNumber = projection.getUserNameSuffix() + 1;
                           return userName + nextNumber;
                         })
                         // if it doesn't already exist; create the default "aavery1"
                         .orElse(userName + "1");
  }

  @Transactional
  public User createUser(UserDto userDto, String userName) {
    User user = new User(userName, userDto.getFirstName(), userDto.getLastName());
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
