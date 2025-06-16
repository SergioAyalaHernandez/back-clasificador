package com.example.ssjava.demo.service;

import com.example.ssjava.demo.dto.UserCreateDTO;
import com.example.ssjava.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllPerson();
    User getPersonById(Integer idPerson);
    User createPerson(UserCreateDTO persona);
    User updatePerson(User persona);

}
