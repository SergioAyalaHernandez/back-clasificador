package com.example.ssjava.demo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "documentos")
public class Documento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Integer id;

    @Lob
    @Column(name = "dato", nullable = false)
    private byte[] dato;

    @Column(name = "nombre_documento", length = 255)
    private String nombreDocumento;

    @Lob
    @Column(name = "contenido_documento")
    private String contenidoDocumento;

    @Column(name = "id_tipologia")
    private Integer idTipologia;

}

