package com.gauravrmsc.superduperdrive.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Credentials {
  private int credentialid;
  private String url;
  private String username;
  private String key;
  private String password;
}
