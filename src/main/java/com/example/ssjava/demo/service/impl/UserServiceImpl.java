package com.example.ssjava.demo.service.impl;

import com.example.ssjava.demo.dto.UserCreateDTO;
import com.example.ssjava.demo.entity.Area;
import com.example.ssjava.demo.entity.User;
import com.example.ssjava.demo.excepciones.BadRequestException;
import com.example.ssjava.demo.mapper.UserMapper;
import com.example.ssjava.demo.repository.AreaRepository;
import com.example.ssjava.demo.repository.UserRepository;
import com.example.ssjava.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository personaRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AreaRepository areaRepository;

    @Override
    public List<User> getAllPerson() {
        return personaRepository.findAll();
    }

    @Override
    public User getPersonById(Integer idPerson) {
        return personaRepository.findById(Long.valueOf(idPerson)).orElse(null);
    }

    @Override
    public User createPerson(UserCreateDTO personaDTO) {
        String email = personaDTO.getEmail();
        if (personaRepository.findByEmail(email).isPresent()) {
            throw new BadRequestException("El correo electrónico ya está registrado");
        }
        User user = userMapper.toEntity(personaDTO);
        user.setPassword(passwordEncoder.encode(personaDTO.getPassword()));

        if (personaDTO.getAreasIds() != null && !personaDTO.getAreasIds().isEmpty()) {
            Set<Area> areas = new HashSet<>();
            for (Integer areaId : personaDTO.getAreasIds()) {
                areaRepository.findById(areaId)
                        .ifPresent(areas::add);
            }
            user.setAreas(areas);
        }

        return personaRepository.save(user);
    }

    @Override
    public User updatePerson(User persona) {
        return personaRepository.save(persona);
    }
}
