package com.example.ssjava.demo.controller;

import com.example.ssjava.demo.entity.TipoUsuario;
import com.example.ssjava.demo.service.TipoUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tipos-usuario")
public class TipoUsuarioController {

  private final TipoUsuarioService tipoUsuarioService;

  @GetMapping
  public ResponseEntity<List<TipoUsuario>> obtenerTodos() {
    return ResponseEntity.ok(tipoUsuarioService.obtenerTodos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<TipoUsuario> obtenerPorId(@PathVariable Integer id) {
    return ResponseEntity.ok(tipoUsuarioService.obtenerPorId(id));
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<TipoUsuario> crear(@RequestBody TipoUsuario tipoUsuario) {
    return new ResponseEntity<>(tipoUsuarioService.crear(tipoUsuario), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<TipoUsuario> actualizar(@PathVariable Integer id, @RequestBody TipoUsuario tipoUsuario) {
    return ResponseEntity.ok(tipoUsuarioService.actualizar(id, tipoUsuario));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
    tipoUsuarioService.eliminar(id);
    return ResponseEntity.noContent().build();
  }
}