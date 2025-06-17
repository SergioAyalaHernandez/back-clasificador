package com.example.ssjava.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {
  private String nombre;
  private String tipo;
  private String contenidoBase64;

  public String getNombreDocumento() {
    return this.nombre;
  }

  public byte[] getDato() {
    if (this.contenidoBase64 == null) {
      return new byte[0];
    }
    return java.util.Base64.getDecoder().decode(this.contenidoBase64);
  }

  public String getContenidoDocumento() {
    return this.tipo;
  }
}
