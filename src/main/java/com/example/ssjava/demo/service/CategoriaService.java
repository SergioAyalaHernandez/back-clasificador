package com.example.ssjava.demo.service;

import com.example.ssjava.demo.entity.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
  List<Categoria> findAll();
  Optional<Categoria> findById(String id);
  Categoria save(Categoria categoria);
  void deleteById(String id);
}