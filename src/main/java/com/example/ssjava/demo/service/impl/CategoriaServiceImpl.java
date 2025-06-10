package com.example.ssjava.demo.service.impl;

import com.example.ssjava.demo.entity.Categoria;
import com.example.ssjava.demo.repository.CategoriaRepository;
import com.example.ssjava.demo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

  private final CategoriaRepository categoriaRepository;

  @Autowired
  public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
    this.categoriaRepository = categoriaRepository;
  }

  @Override
  public List<Categoria> findAll() {
    return categoriaRepository.findAll();
  }

  @Override
  public Optional<Categoria> findById(String id) {
    return categoriaRepository.findById(id);
  }

  @Override
  public Categoria save(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }

  @Override
  public void deleteById(String id) {
    categoriaRepository.deleteById(id);
  }
}