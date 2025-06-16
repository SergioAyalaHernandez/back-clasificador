package com.example.ssjava.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

  @Column(name = "fecha_creacion", nullable = false, updatable = false)
  private LocalDateTime fechaCreacion;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "tipo_usuario_id")
  @JsonIgnoreProperties("tipoUsuario")
  private TipoUsuario tipoUsuario;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "usuario_areas",
          joinColumns = @JoinColumn(name = "usuario_id"),
          inverseJoinColumns = @JoinColumn(name = "area_id")
  )
  @JsonIgnoreProperties("usuarios")
  private Set<Area> areas = new HashSet<>();

}
