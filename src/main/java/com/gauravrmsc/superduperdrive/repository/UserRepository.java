package com.gauravrmsc.superduperdrive.repository;

import com.gauravrmsc.superduperdrive.repository.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository /*extends CrudRepository<UserEntity, Long>*/ {
  UserEntity findByUserId(String userId);
}
