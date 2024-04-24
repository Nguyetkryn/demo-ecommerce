package com.example.demoecommerce.moamoa;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderExample {
    public static void main(String[] args) {
        String password = "123456";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);

        System.out.println("Original Password: " + password);
        System.out.println("Hashed Password: " + hashedPassword);
    }
}
