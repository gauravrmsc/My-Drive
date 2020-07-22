package com.gauravrmsc.superduperdrive.controller;

import com.gauravrmsc.superduperdrive.model.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//@Controller
public class DriveErrorController implements ErrorController {
  @Override
  public String getErrorPath() {
    return "/error";
  }

  @GetMapping("/error")
  public String error(Model model) {
    Result result = new Result();
    result.setErrorMessage("Unknown Error");
    model.addAttribute("result", result);
    return "result";
  }
  @PostMapping("/error")
  public String errorForPost(Model model) {
    return "redirect:/error";
  }
}
