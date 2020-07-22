package com.gauravrmsc.superduperdrive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
  String errorMessage;
  String successMessage;

  public void setErrorMessage(String errorMessage) {
    this.successMessage = null;
    this.errorMessage = errorMessage;
  }

  public void setSuccessMessage(String successMessage) {
    this.errorMessage = null;
    this.successMessage = successMessage;
  }

  public String toString() {
    return String.format("error=%s&success=%s", errorMessage, successMessage);
  }
}
