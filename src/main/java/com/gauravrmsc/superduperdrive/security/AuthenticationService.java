package com.gauravrmsc.superduperdrive.security;

import com.gauravrmsc.superduperdrive.repository.entity.UserEntity;
import com.gauravrmsc.superduperdrive.repository.repositoryservice.UserRepositoryService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements AuthenticationProvider {
  @Autowired
  UserRepositoryService userRepositoryService;
  @Autowired
  EncryptionAndHashing hashingService;
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String userId = authentication.getName();
    String password = authentication.getCredentials().toString();
    UserEntity user = userRepositoryService.getUser(userId);
    if(user == null) {
      return null;
    } else if (hashingService.matchPassword(password, user.getPassword())){
      return new UsernamePasswordAuthenticationToken(userId, password, new ArrayList<>());
    }
    return null;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return  authentication.equals(UsernamePasswordAuthenticationToken.class) ;
  }
}
