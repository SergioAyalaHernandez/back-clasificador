package com.example.ssjava.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
  private String description;
  private String priority;
  private String status;
  private String categoryId;
  private List<DocumentoDTO> documentos;
}