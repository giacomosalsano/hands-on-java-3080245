package bank.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

  public static String hashPassword(String plainTextPassword) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] encodedHash = digest.digest(plainTextPassword.getBytes(StandardCharsets.UTF_8));

      StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
      for (byte b : encodedHash) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }
      return hexString.toString();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Internal encryption error", e);
    }
  }
}