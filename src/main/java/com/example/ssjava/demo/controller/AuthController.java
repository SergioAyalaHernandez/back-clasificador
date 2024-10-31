package com.example.ssjava.demo.controller;

import com.example.ssjava.demo.controller.config.JwtUtil;
import com.example.ssjava.demo.dto.AuthResponseDto;
import com.example.ssjava.demo.dto.LoginDto;
import com.example.ssjava.demo.entity.PersonaEntity;
import com.example.ssjava.demo.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PersonaRepository personaRepository;
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto dto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPass());
        Authentication authentication = this.authenticationManager.authenticate(login);
        String jwt = this.jwtUtil.create(dto.getEmail());
        PersonaEntity user = personaRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        AuthResponseDto responseDto = new AuthResponseDto(jwt, dto.getEmail(), user.getIdPersona());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).body(responseDto);
    }
}
