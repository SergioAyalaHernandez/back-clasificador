package com.example.ssjava.demo.service.impl;

import com.example.ssjava.demo.entity.Pqr;
import com.example.ssjava.demo.repository.PqrRepository;
import com.example.ssjava.demo.service.PqrService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PqrServiceImpl implements PqrService {

    private final PqrRepository pqrRepository;


    @Override
    public Pqr crearPqr(Pqr pqr) {
        pqr.setFechaCreacion(LocalDateTime.now());
        return pqrRepository.save(pqr);
    }

    @Override
    public Optional<Pqr> obtenerPqrPorId(Integer id) {
        return pqrRepository.findById(id);
    }

    @Override
    public List<Pqr> obtenerTodosPqrs() {
        return pqrRepository.findAll();
    }

    @Override
    public Pqr actualizarPqr(Integer id, Pqr pqrActualizado) {
        return pqrRepository.findById(id).map(pqr -> {
            pqr.setIdPersona(pqrActualizado.getIdPersona());
            pqr.setIdPrograma(pqrActualizado.getIdPrograma());
            pqr.setIdDocumento(pqrActualizado.getIdDocumento());
            pqr.setNombreCaso(pqrActualizado.getNombreCaso());
            pqr.setIdTipologia(pqrActualizado.getIdTipologia());
            pqr.setFechaCierreCaso(pqrActualizado.getFechaCierreCaso());
            pqr.setFechaClasificacion(pqrActualizado.getFechaClasificacion());
            pqr.setPlazoDiasRespuesta(pqrActualizado.getPlazoDiasRespuesta());
            return pqrRepository.save(pqr);
        }).orElseThrow(() -> new RuntimeException("PQR no encontrado"));
    }

    @Override
    public void eliminarPqr(Integer id) {
        pqrRepository.deleteById(id);
    }
}