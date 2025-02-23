package com.kgj.project.manager.bcrypt;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class Bcrypt implements EncryptHelper{

    @Override
    public String encrypt(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt());
    }

    @Override
    public boolean isMatch(String plainText, String encryptedText) {
        return BCrypt.checkpw(plainText, encryptedText);
    }
}
