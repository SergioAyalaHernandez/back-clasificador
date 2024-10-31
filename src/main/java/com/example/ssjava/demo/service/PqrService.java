package com.example.ssjava.demo.service;

import com.example.ssjava.demo.entity.Pqr;

import java.util.List;
import java.util.Optional;

public interface PqrService {
    Pqr crearPqr(Pqr pqr);
    Optional<Pqr> obtenerPqrPorId(Integer id);
    List<Pqr> obtenerTodosPqrs();
    Pqr actualizarPqr(Integer id, Pqr pqrActualizado);
    void eliminarPqr(Integer id);
}
