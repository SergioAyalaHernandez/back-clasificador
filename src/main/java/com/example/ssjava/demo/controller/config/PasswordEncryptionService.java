package com.example.ssjava.demo.controller.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptionService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public static void main(String[] args) {
        PasswordEncryptionService service = new PasswordEncryptionService();
        service.passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "password123";
        String encryptedPassword = service.encryptPassword(rawPassword);
        System.out.println("Encrypted password: " + encryptedPassword);
    }
}