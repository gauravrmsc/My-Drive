package com.gauravrmsc.superduperdrive.repository.repositoryservice;

import com.gauravrmsc.superduperdrive.mappers.CredentialsMapper;
import com.gauravrmsc.superduperdrive.model.Credentials;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredentialsRepositorService {
  @Autowired
  CredentialsMapper repository;

  public void addCredentails(Credentials credentials, long userId) {
    repository.insertCredentials(credentials, userId);
  }

  public List<Credentials> findByUserId(long userId) {
    return repository.findByUserId(userId);
  }

  public void deleteCredential(long credentailId, long userId) throws Exception {
    List<Credentials> credentials = findByUserId(userId);
    for (Credentials credential : credentials) {
      if (credential.getCredentialId() == credentailId) {
        repository.deleteCredentials(credentailId);
        return;
      }
    }
    throw new Exception("Credential Not Found");
  }

  public void updateCredential(Credentials updatedCredential, long userId) throws Exception{
    List<Credentials> credentials = findByUserId(userId);
    for (Credentials credential : credentials) {
      if (credential.getCredentialId() == updatedCredential.getCredentialId()) {
        repository.updateCredentials(updatedCredential);
        return;
      }
    }
    throw new Exception("Credential Not Found");
  }
}
