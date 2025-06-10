package com.example.ssjava.demo.controller.config;

import com.example.ssjava.demo.entity.User;
import com.example.ssjava.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    private final UserRepository personaRepository;
    @Autowired
    public UserSecurityService(UserRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User persona = personaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No existe ese correo"));
        System.out.println(persona);
        return org.springframework.security.core.userdetails.User.builder()
                .username(persona.getEmail())
                .password(persona.getPassword()) // La contraseña ya debe estar encriptada en la base de datos
                .roles(persona.getTipoUsuario())
                .accountLocked(false)
                .disabled(false)
                .build();
    }

}
