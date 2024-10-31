package com.example.ssjava.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PQRS")
public class Pqr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPqr;

    @Column(nullable = false)
    private Integer idPersona;

    @Column(nullable = false)
    private Integer idPrograma;

    @Column(nullable = false)
    private Integer idDocumento;

    @Column(name = "nombre_caso", nullable = false)
    private String nombreCaso;

    @Column(nullable = false)
    private Integer idTipologia;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_cierre_caso")
    private LocalDateTime fechaCierreCaso;

    @Column(name = "fecha_clasificacion")
    private LocalDateTime fechaClasificacion;

    @Column(name = "plazo_dias_respuesta", nullable = false)
    private Integer plazoDiasRespuesta;

}
