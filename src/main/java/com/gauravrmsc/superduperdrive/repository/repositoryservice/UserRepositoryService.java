package com.gauravrmsc.superduperdrive.repository.repositoryservice;

import com.gauravrmsc.superduperdrive.mappers.UserMapper;
import com.gauravrmsc.superduperdrive.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryService {

  @Autowired
  UserMapper userRepository;

  public UserEntity getUser(String username) {
    return userRepository.findByUsername(username);
  }
  public boolean userExists(String username) {
    if (userRepository.findByUsername(username) != null) {
      return true;
    } else {
      return false;
    }
  }
  public void addUser(UserEntity user) {
    userRepository.save(user);
  }
}
