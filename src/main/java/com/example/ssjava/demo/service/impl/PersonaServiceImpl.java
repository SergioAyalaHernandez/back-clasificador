package com.example.ssjava.demo.service.impl;

import com.example.ssjava.demo.entity.User;
import com.example.ssjava.demo.excepciones.BadRequestException;
import com.example.ssjava.demo.repository.UserRepository;
import com.example.ssjava.demo.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    private final UserRepository personaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllPerson() {
        return personaRepository.findAll();
    }

    @Override
    public User getPersonById(Integer idPerson) {
        return personaRepository.findById(Long.valueOf(idPerson)).orElse(null);
    }

    @Override
    public User createPerson(User persona) {
        String email = persona.getEmail();
        if (personaRepository.findByEmail(email).isPresent()) {
            throw new BadRequestException("El correo electrónico ya está registrado");
        }
        String encodedPassword = passwordEncoder.encode(persona.getPassword());
        persona.setPassword(encodedPassword);
        persona.setFechaCreacion(LocalDateTime.now());
        return personaRepository.save(persona);
    }

    @Override
    public User updatePerson(User persona) {
        return personaRepository.save(persona);
    }
}
