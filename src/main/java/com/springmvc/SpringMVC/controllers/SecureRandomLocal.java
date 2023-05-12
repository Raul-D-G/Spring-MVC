package com.springmvc.SpringMVC.controllers;

import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class SecureRandomLocal {

// ...

    private static final SecureRandom RANDOM = new SecureRandom();

    public static Integer generateRandomId() {
        byte[] bytes = new byte[4];
        RANDOM.nextBytes(bytes);
        return Math.abs(ByteBuffer.wrap(bytes).getInt());
    }
}
