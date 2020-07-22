package com.gauravrmsc.superduperdrive.controller;

import com.gauravrmsc.superduperdrive.model.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Component
public class ErrorHandler implements ErrorController {
  @Override
  public String getErrorPath() {
    return "/error";
  }

}
