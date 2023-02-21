package com.minihouse.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    private final String SHA_512 = "SHA-512";

    public String hashPassword(String originPassword) {
        return createHashPassword(originPassword, SHA_512);
    }

    public boolean isMatched(String originPassword, String hashedPassword) {
        return createHashPassword(originPassword, SHA_512).equals(hashedPassword);
    }

    private String createHashPassword(String originPassword, String algorithm) {
        String hashPassword;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(originPassword.getBytes(StandardCharsets.UTF_8));
            hashPassword = String.format("%0128x", new BigInteger(1, messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hashPassword;
    }
}
