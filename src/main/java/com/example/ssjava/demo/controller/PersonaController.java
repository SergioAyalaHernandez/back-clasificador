package com.example.ssjava.demo.controller;

import com.example.ssjava.demo.entity.PersonaEntity;
import com.example.ssjava.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<PersonaEntity>> getAllPersonas() {
        List<PersonaEntity> personas = personaService.getAllPerson();
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaEntity> getPersonaById(@PathVariable("id") Integer idPerson) {
        PersonaEntity persona = personaService.getPersonById(idPerson);
        if (persona == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(persona);
    }

    @PostMapping
    public ResponseEntity<PersonaEntity> createPersona(@RequestBody PersonaEntity persona) {
        PersonaEntity createdPersona = personaService.createPerson(persona);
        return ResponseEntity.ok(createdPersona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaEntity> updatePersona(@PathVariable("id") Integer idPerson, @RequestBody PersonaEntity personaDetails) {
        PersonaEntity existingPersona = personaService.getPersonById(idPerson);
        if (existingPersona == null) {
            return ResponseEntity.notFound().build();
        }
        personaDetails.setIdPersona(idPerson);
        PersonaEntity updatedPersona = personaService.updatePerson(personaDetails);
        return ResponseEntity.ok(updatedPersona);
    }

}
