package com.example.ssjava.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tipos_usuario")
public class TipoUsuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, unique = true, length = 50)
  private String nombre;

  @Column(length = 255)
  private String descripcion;

  @OneToMany(mappedBy = "tipoUsuario")
  @JsonIgnore
  private Set<User> usuarios = new HashSet<>();
}