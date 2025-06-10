package com.example.ssjava.demo.controller.config;

import com.example.ssjava.demo.entity.User;
import com.example.ssjava.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordMigrationService {

    @Autowired
    private UserRepository personaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void migratePasswords() {
        List<User> personas = personaRepository.findAll();
        for (User persona : personas) {
            String rawPassword = persona.getPassword();
            if (!passwordEncoder.matches(rawPassword, persona.getPassword())) {
                String encodedPassword = passwordEncoder.encode(rawPassword);
                persona.setPassword(encodedPassword);
                personaRepository.save(persona);
            }
        }
    }
}
