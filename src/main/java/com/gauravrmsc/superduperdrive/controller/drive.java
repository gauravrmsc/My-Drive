package com.gauravrmsc.superduperdrive.controller;

import com.gauravrmsc.superduperdrive.model.NewUser;
import com.gauravrmsc.superduperdrive.model.Result;
import com.gauravrmsc.superduperdrive.repository.entity.UserEntity;
import com.gauravrmsc.superduperdrive.service.CredentialsService;
import com.gauravrmsc.superduperdrive.service.UserService;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class drive {
  @Autowired
  UserService userService;
  @Autowired
  CredentialsService credentialsService;

  @GetMapping(value = {"/", ""})
  public String welcome() {
    return "redirect:/home";
  }

  @GetMapping("/home")
  public String home(Principal principal, Model model) throws Exception{
    addUserInfo(principal, model);
    credentialsService.addCredentialsToModel(model);
    return "home";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/signup")
  public String signup(Model model) {
    model.addAttribute("result", null);
    return "signup";
  }

  @PostMapping("/signup")
  public String signupSubmit(Model model, NewUser newUser) {
    Result result = userService.addNewUser(newUser);
    model.addAttribute("result", result);
    return "signup";
  }

  @RequestMapping("/error")
  public String result(Result result, Model model) {
    if (result.getErrorMessage() == null) {
      result = new Result();
      result.setErrorMessage("Unknown Error");
      model.addAttribute("result", result);
    }
    return "result";
  }

  public void addUserInfo(Principal principal, Model model) {
    String userId = principal.getName();
    UserEntity userEntity = userService.getUser(userId);
    model.addAttribute("user", userEntity);
  }
}



