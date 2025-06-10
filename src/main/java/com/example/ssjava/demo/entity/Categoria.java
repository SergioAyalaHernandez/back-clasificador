package com.example.ssjava.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "priority")
  private String priority;

  @ManyToOne
  @JoinColumn(name = "area_id")
  private Area assignedArea;

}