package com.example.ssjava.demo.service.impl;

import com.example.ssjava.demo.entity.TipoUsuario;
import com.example.ssjava.demo.repository.TipoUsuarioRepository;
import com.example.ssjava.demo.service.TipoUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

  private final TipoUsuarioRepository tipoUsuarioRepository;


  @Override
  public List<TipoUsuario> obtenerTodos() {
    return tipoUsuarioRepository.findAll();
  }

  @Override
  public TipoUsuario obtenerPorId(Integer id) {
    return tipoUsuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("TipoUsuario no encontrado con id: " + id));
  }

  @Override
  @Transactional
  public TipoUsuario crear(TipoUsuario tipoUsuario) {
    if (tipoUsuarioRepository.existsByNombre(tipoUsuario.getNombre())) {
      throw new RuntimeException("Ya existe un tipo de usuario con ese nombre");
    }
    return tipoUsuarioRepository.save(tipoUsuario);
  }

  @Override
  @Transactional
  public TipoUsuario actualizar(Integer id, TipoUsuario tipoUsuario) {
    TipoUsuario existente = obtenerPorId(id);

    if (!existente.getNombre().equals(tipoUsuario.getNombre()) &&
            tipoUsuarioRepository.existsByNombre(tipoUsuario.getNombre())) {
      throw new RuntimeException("Ya existe un tipo de usuario con ese nombre");
    }

    existente.setNombre(tipoUsuario.getNombre());
    existente.setDescripcion(tipoUsuario.getDescripcion());

    return tipoUsuarioRepository.save(existente);
  }

  @Override
  @Transactional
  public void eliminar(Integer id) {
    if (!tipoUsuarioRepository.existsById(id)) {
      throw new RuntimeException("TipoUsuario no encontrado con id: " + id);
    }
    tipoUsuarioRepository.deleteById(id);
  }
}