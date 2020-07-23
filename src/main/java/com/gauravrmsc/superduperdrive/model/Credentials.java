package com.gauravrmsc.superduperdrive.model;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Credentials {
  private Long credentialId;
  private String url;
  private String username;
  private String key1;
  private String password;

  public String getKey() {
    return key1;
  }
  public void setKey(String key) {
    this.key1 = key;
  }
}
