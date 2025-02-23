package com.kgj.project.manager.bcrypt;

public interface EncryptHelper {

    String encrypt(String plainText);
    boolean isMatch(String plainText, String encryptedText);
}
