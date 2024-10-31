package com.example.ssjava.demo.service;

import com.example.ssjava.demo.entity.Documento;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentoService {
    Documento guardarDocumento(MultipartFile file, Integer idTipologia) throws Exception;
}