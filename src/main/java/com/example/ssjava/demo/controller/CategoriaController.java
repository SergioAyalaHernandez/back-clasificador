package com.example.ssjava.demo.controller;

import com.example.ssjava.demo.entity.Categoria;
import com.example.ssjava.demo.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categorias")
public class CategoriaController {

  private final CategoriaService categoriaService;

  @GetMapping
  public ResponseEntity<List<Categoria>> getAllCategorias() {
    return ResponseEntity.ok(categoriaService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Categoria> getCategoriaById(@PathVariable String id) {
    return categoriaService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
    Categoria savedCategoria = categoriaService.save(categoria);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoria);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Categoria> updateCategoria(@PathVariable String id, @RequestBody Categoria categoria) {
    return categoriaService.findById(id)
            .map(existingCategoria -> {
              categoria.setId(id);
              return ResponseEntity.ok(categoriaService.save(categoria));
            })
            .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategoria(@PathVariable String id) {
    if (categoriaService.findById(id).isPresent()) {
      categoriaService.deleteById(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}