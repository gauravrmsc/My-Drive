package com.gauravrmsc.superduperdrive.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionAndHashing {
  private static final int ENC_STRENGTH = 13;
  private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(ENC_STRENGTH);
  private static Cipher cipher = null;

  static {
    try {
      cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    }
  }

  public String securePassword(String password) {
    SecureRandom random = new SecureRandom();
    return encoder.encode(password);
  }

  public boolean matchPassword(String password, String hash) {
    return encoder.matches(password, hash);
  }

  public String encryptData(String data, String key) throws Exception {
    if (cipher == null) {
      throw new NullPointerException("Cipher is null");
    }
    byte[] encryptedValue = null;
    SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    encryptedValue = cipher.doFinal(data.getBytes("UTF-8"));
    return Base64.getEncoder().encodeToString(encryptedValue);
  }

  public String decryptData(String data, String key) throws Exception {
    if (cipher == null) {
      throw new NullPointerException("Cipher is null");
    }
    byte[] decryptedValue = null;
    SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    decryptedValue = cipher.doFinal(Base64.getDecoder().decode(data));
    return new String(decryptedValue);
  }
  public String generateKey(){
    byte[] keyBytes = new byte[32];
    SecureRandom secureRandom = new SecureRandom();
    secureRandom.nextBytes(keyBytes);
    return keyBytes.toString();
  }
}
