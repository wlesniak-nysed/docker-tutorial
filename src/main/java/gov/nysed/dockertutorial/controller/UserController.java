package gov.nysed.dockertutorial.controller;

import gov.nysed.dockertutorial.dto.UserDto;
import gov.nysed.dockertutorial.model.User;
import gov.nysed.dockertutorial.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/hello")
  String sayHello() {
    return "Hello World";
  }

  // GET endpoint: Retrieves all saved users
  @GetMapping("/all")
  public List<UserDto> getAllUsers() {
    return userService.getAllUsers();
  }

  // POST endpoint: Saves data to H2 database
  @PostMapping("/create")
  public UserDto createUser(@RequestBody UserDto userDto) {
    User newUser = userService.createUser(userDto);
    return new UserDto(newUser.getId(), newUser.getUserName(), newUser.getFirstName(),
                             newUser.getLastName());
  }

  // DELETE endpoint: deletes data from H2 database
  @DeleteMapping("/delete")
  public ResponseEntity<Object> deleteUser(@RequestBody UserDto userDto) {
    return userService.deleteUser(userDto);
  }

}
