package com.gauravrmsc.superduperdrive.mappers;

import com.gauravrmsc.superduperdrive.model.Credentials;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CredentialsMapper {

  @Select("SELECT * FROM CREDENTIALS")
  List<Credentials> findAll();

  @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
  Credentials findOne(long credentialId);

  @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
  List<Credentials> findByUserId(long userId);

  @Insert(
      "INSERT INTO CREDENTIALS (url, username, key1, password, userId) VALUES (#{credential.url}, #{credential.username}, #{credential.key}, #{credential.password}, #{userId})")
  int insertCredentials(Credentials credential, long userId);

  @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
  int deleteCredentials(long credentialId);

  @Update(
      "UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key1 = #{key}, password = #{password} WHERE credentialId = #{credentialId}")
  int updateCredentials(Credentials credential);

}
