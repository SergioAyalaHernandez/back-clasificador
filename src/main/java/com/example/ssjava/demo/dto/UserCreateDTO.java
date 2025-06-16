package com.example.ssjava.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserCreateDTO {
  private String nombres;
  private String apellidos;
  private String email;
  private String password;
  private String celular;
  private Integer tipoUsuarioId;
  private Set<Integer> areasIds;
}