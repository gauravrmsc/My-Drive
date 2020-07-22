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

  @Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialid}")
  Credentials findOne(int credentialid);

  @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userid}")
  List<Credentials> findByUserId(int userid);

  @Insert(
      "INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{credential.url}, #{credential.username}, #{credential.key}, #{credential.password}, #{userid})")
  int insertCredentials(Credentials credential, int userid);

  @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialid}")
  int deleteCredentials(int credentialid);

  @Update(
      "UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialid = #{credentialid}")
  int updateCredentials(Credentials credential);

}
