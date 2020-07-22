package com.gauravrmsc.superduperdrive.mappers;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

//@Repository
@Mapper
public interface UserMapper {

  @Select("SELECT * FROM USERS")
  List<com.gauravrmsc.superduperdrive.repository.entity.UserEntity> findAll();

  @Select("SELECT * FROM USERS WHERE userid = #{userid}")
  com.gauravrmsc.superduperdrive.repository.entity.UserEntity findOne(int userid);

  @Select("SELECT * FROM USERS WHERE username = #{username}")
  com.gauravrmsc.superduperdrive.repository.entity.UserEntity findByUsername(String username);

  @Insert(
      "INSERT INTO USERS (username, password,firstname, lastname) VALUES (#{username}, #{password}, #{firstName}, #{lastName})")
  int save(com.gauravrmsc.superduperdrive.repository.entity.UserEntity userEntity);

  @Delete("DELETE FROM USERS WHERE username = #{username}")
  int deleteUser(String username);

  @Update(
      "UPDATE USERS SET username = #{username}, password = #{password}, firstname = #{firstName}, lastname = #{lastName} WHERE userid = #{userid}")
  int updateUser(com.gauravrmsc.superduperdrive.repository.entity.UserEntity userEntity);

}
