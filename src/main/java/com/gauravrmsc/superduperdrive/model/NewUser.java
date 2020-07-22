package com.gauravrmsc.superduperdrive.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class NewUser {
  @NonNull
  String username;
  @NonNull
  String password;
  @NonNull
  String firstName;
  @NonNull
  String lastName;
}
