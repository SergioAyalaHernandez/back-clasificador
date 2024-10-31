package com.example.ssjava.demo.repository;

import com.example.ssjava.demo.entity.PersonaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface PersonaRepository extends ListCrudRepository<PersonaEntity, Long> {
    @Query(value = "select * from ssjava.persona where email = :email", nativeQuery = true)
    Optional<PersonaEntity> findByEmail(String email);

}
