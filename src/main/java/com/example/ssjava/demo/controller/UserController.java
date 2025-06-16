package com.example.ssjava.demo.controller;

import com.example.ssjava.demo.dto.UserCreateDTO;
import com.example.ssjava.demo.entity.User;
import com.example.ssjava.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

  private UserService personaService;

  @GetMapping
  public ResponseEntity<List<User>> getAllPersonas() {
    List<User> personas = personaService.getAllPerson();
    return ResponseEntity.ok(personas);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getPersonaById(@PathVariable("id") Integer idPerson) {
    User persona = personaService.getPersonById(idPerson);
    if (persona == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(persona);
  }

  @PostMapping
  public ResponseEntity<User> createPersona(@RequestBody UserCreateDTO persona) {
    User createdPersona = personaService.createPerson(persona);
    return ResponseEntity.ok(createdPersona);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updatePersona(@PathVariable("id") Integer idPerson, @RequestBody User personaDetails) {
    User existingPersona = personaService.getPersonById(idPerson);
    if (existingPersona == null) {
      return ResponseEntity.notFound().build();
    }
    personaDetails.setIdPersona(idPerson);
    User updatedPersona = personaService.updatePerson(personaDetails);
    return ResponseEntity.ok(updatedPersona);
  }

}
