package com.gauravrmsc.superduperdrive.service;

import com.gauravrmsc.superduperdrive.model.NewUser;
import com.gauravrmsc.superduperdrive.model.Result;
import com.gauravrmsc.superduperdrive.repository.entity.UserEntity;
import com.gauravrmsc.superduperdrive.repository.repositoryservice.UserRepositoryService;
import com.gauravrmsc.superduperdrive.security.EncryptionAndHashing;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  UserRepositoryService userRepositoryService;
  @Autowired
  EncryptionAndHashing encService;

  public Result addNewUser(NewUser newUser) {
    Result result = new Result();
    if (userRepositoryService.userExists(newUser.getUsername())) {
      result.setErrorMessage("User Already Exists");
    } else {
      newUser.setPassword(encService.securePassword(newUser.getPassword()));
      UserEntity newUserEntity = new UserEntity();
      BeanUtils.copyProperties(newUser, newUserEntity);
      userRepositoryService.addUser(newUserEntity);
      result.setSuccessMessage("Account Created Successfully");
    }
    return result;
  }

  public UserEntity getUser(String userId) {
    return userRepositoryService.getUser(userId);
  }
}
