package com.example.ssjava.demo.service;

import com.example.ssjava.demo.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {
    List<PersonaEntity> getAllPerson();
    PersonaEntity getPersonById(Integer idPerson);
    PersonaEntity createPerson(PersonaEntity persona);
    PersonaEntity updatePerson(PersonaEntity persona);

}
