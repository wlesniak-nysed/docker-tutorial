package gov.nysed.dockertutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserDto {

  Long id;
  String userName;
  String firstName;
  String lastName;

}
