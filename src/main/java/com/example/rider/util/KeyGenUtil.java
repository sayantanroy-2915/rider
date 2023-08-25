package com.example.rider.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGenUtil {

    public static KeyPair generateKey() {

        KeyPair keyPair;

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch(Exception e) {
            throw new IllegalStateException();
        }

        return keyPair;
    }
}
