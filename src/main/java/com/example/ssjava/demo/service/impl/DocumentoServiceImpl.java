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
    public Documento guardarDocumento(MultipartFile file, Integer idTipologia) throws Exception {
        Documento documento = new Documento();
        documento.setDato(file.getBytes());

        documento.setNombreDocumento(file.getOriginalFilename());

        if (Objects.requireNonNull(file.getOriginalFilename()).endsWith(".docx")) {
            documento.setContenidoDocumento(extraerTextoDocx(file.getInputStream()));
        } else if (file.getOriginalFilename().endsWith(".pdf")) {
            documento.setContenidoDocumento(extraerTextoPdf(file.getInputStream()));
        } else {
            throw new IllegalArgumentException("Formato de archivo no soportado. Solo se aceptan .docx y .pdf.");
        }

        return documentoRepository.save(documento);
    }

    private String extraerTextoDocx(InputStream inputStream) throws Exception {
        XWPFDocument document = new XWPFDocument(inputStream);
        StringBuilder text = new StringBuilder();

        document.getParagraphs().forEach(paragraph -> text.append(paragraph.getText()).append("\n"));
        document.close();

        return text.toString();
    }

    private String extraerTextoPdf(InputStream inputStream) throws Exception {
        PDDocument pdfDocument = PDDocument.load(inputStream);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(pdfDocument);
        pdfDocument.close();
        return text;
    }
}

