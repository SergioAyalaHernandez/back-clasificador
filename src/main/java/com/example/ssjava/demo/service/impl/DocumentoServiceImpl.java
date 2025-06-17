package com.example.ssjava.demo.service.impl;

import com.example.ssjava.demo.entity.Documento;
import com.example.ssjava.demo.repository.DocumentoRepository;
import com.example.ssjava.demo.service.DocumentoService;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository documentoRepository;

    @Override
    public Documento guardarDocumento(Documento documento) {
        return documentoRepository.save(documento);
    }

}

