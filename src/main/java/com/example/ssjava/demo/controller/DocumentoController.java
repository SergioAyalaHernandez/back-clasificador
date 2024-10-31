package com.example.ssjava.demo.controller;

import com.example.ssjava.demo.entity.Documento;
import com.example.ssjava.demo.service.DocumentoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/api/documentos")
public class DocumentoController {

    private final DocumentoService documentoService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocumento(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("idTipologia") Integer idTipologia) {
        try {
            Documento documento = documentoService.guardarDocumento(file, idTipologia);
            return ResponseEntity.ok("Documento guardado con ID: " + documento.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar el documento: " + e.getMessage());
        }
    }
}