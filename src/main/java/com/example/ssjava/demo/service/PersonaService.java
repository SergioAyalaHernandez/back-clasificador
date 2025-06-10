package com.example.ssjava.demo.service;

import com.example.ssjava.demo.entity.User;

import java.util.List;

public interface PersonaService {
    List<User> getAllPerson();
    User getPersonById(Integer idPerson);
    User createPerson(User persona);
    User updatePerson(User persona);

}
