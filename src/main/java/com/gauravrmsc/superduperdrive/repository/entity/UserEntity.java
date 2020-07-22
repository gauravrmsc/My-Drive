package com.gauravrmsc.superduperdrive.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

  long userId;
  String username;
  String password;
  String firstName;
  String lastName;
}
