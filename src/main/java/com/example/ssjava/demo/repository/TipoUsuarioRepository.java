package com.example.ssjava.demo.repository;

import com.example.ssjava.demo.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
  boolean existsByNombre(String nombre);
}