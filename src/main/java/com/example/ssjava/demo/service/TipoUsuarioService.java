package com.example.ssjava.demo.service;

import com.example.ssjava.demo.entity.TipoUsuario;
import java.util.List;

public interface TipoUsuarioService {
  List<TipoUsuario> obtenerTodos();
  TipoUsuario obtenerPorId(Integer id);
  TipoUsuario crear(TipoUsuario tipoUsuario);
  TipoUsuario actualizar(Integer id, TipoUsuario tipoUsuario);
  void eliminar(Integer id);
}