package com.gauravrmsc.superduperdrive.service;

import com.gauravrmsc.superduperdrive.mappers.UserMapper;
import com.gauravrmsc.superduperdrive.model.Credentials;
import com.gauravrmsc.superduperdrive.model.Result;
import com.gauravrmsc.superduperdrive.repository.entity.UserEntity;
import com.gauravrmsc.superduperdrive.repository.repositoryservice.CredentialsRepositoryService;
import com.gauravrmsc.superduperdrive.repository.repositoryservice.UserRepositoryService;
import com.gauravrmsc.superduperdrive.security.EncryptionAndHashing;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CredentialsService {

  @Autowired
  EncryptionAndHashing encService;

  @Autowired
  UserRepositoryService userRepositoryService;

  @Autowired
  CredentialsRepositoryService credentialsRepositoryService;
  @Autowired
  UserMapper userMapper;



  public Result addNewCredentials(Credentials credentials, String username) throws Exception {

    Result result = new Result();
    UserEntity user = userRepositoryService.getUser(username);

    String key = encService.generateKey();
    String url = encService.encryptData(credentials.getUrl(), key);
    String credentailsUsername = encService.encryptData(credentials.getUsername(), key);
    String credentailsPassword = encService.encryptData(credentials.getPassword(), key);
    Credentials encryptedCredentials =
        new Credentials(null, url, credentailsUsername, key, credentailsPassword);
    if (credentials.getCredentialId() != -1) {
      try{
        encryptedCredentials.setCredentialId(credentials.getCredentialId());
        credentialsRepositoryService.updateCredential(encryptedCredentials, user.getUserId());
        result.setSuccessMessage("Details Updated Successfully");
        return result;
      } catch (Exception e) {
        result.setErrorMessage("Specified Details does not exist");
        return result;
      }

    }
    credentialsRepositoryService.addCredentails(encryptedCredentials, user.getUserId());
    result.setSuccessMessage("Credentials Added Successfully");
    return result;
  }

  public void addCredentialsToModel(Model model) throws Exception {
    UserEntity user = (UserEntity) model.getAttribute("user");
    long userId = user.getUserId();
    List<Credentials> credentials = credentialsRepositoryService.findByUserId(userId);
    decryptCredentials(credentials);
    model.addAttribute("credentials", credentials);
  }

  public Result deleteCredentals(long credentialsId, String username) {
    Result result = new Result();
    UserEntity user = userRepositoryService.getUser(username);
    long userId = user.getUserId();
    try{
      credentialsRepositoryService.deleteCredential(credentialsId, userId);
      result.setSuccessMessage("Credentail deleted Successfully");
    } catch (Exception e) {
      result.setErrorMessage("Credential does not exist");
    }
    return result;
  }

  public void decryptCredentials(List<Credentials> credentials) throws Exception {
    for (Credentials credential : credentials) {
      String encryptedUserName = credential.getUsername();
      String encryptedPassword = credential.getPassword();
      String encryptedUrl = credential.getUrl();
      String key = credential.getKey();
      credential.setUsername(encService.decryptData(encryptedUserName, key));
      credential.setPassword(encService.decryptData(encryptedPassword, key));
      credential.setUrl(encService.decryptData(encryptedUrl, key));
      credential.setKey("");
    }
  }
}
