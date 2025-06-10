package com.example.ssjava.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idPersona;

  @Column(nullable = false, length = 65)
  private String nombres;

  @Column(nullable = false, length = 65)
  private String apellidos;

  @Column(nullable = false, length = 100)
  private String email;

  @Column(nullable = false, length = 75)
  private String password;

  @Column(nullable = false, length = 20)
  private String celular;

  @Column(nullable = false, length = 20)
  private String tipoUsuario;

  @Column(name = "fecha_creacion", nullable = false, updatable = false)
  private LocalDateTime fechaCreacion;

}
