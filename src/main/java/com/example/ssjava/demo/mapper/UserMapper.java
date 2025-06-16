package com.example.ssjava.demo.mapper;

import com.example.ssjava.demo.dto.UserCreateDTO;
import com.example.ssjava.demo.entity.User;
import com.example.ssjava.demo.entity.TipoUsuario;
import com.example.ssjava.demo.entity.Area;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "idPersona", ignore = true)
  @Mapping(target = "fechaCreacion", expression = "java(java.time.LocalDateTime.now())")
  @Mapping(target = "tipoUsuario", ignore = true)
  @Mapping(target = "areas", ignore = true)
  User toEntity(UserCreateDTO dto);
}