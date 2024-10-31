package com.example.ssjava.demo.repository;

import com.example.ssjava.demo.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
}
