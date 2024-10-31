package com.example.ssjava.demo.controller;

import com.example.ssjava.demo.entity.Pqr;
import com.example.ssjava.demo.service.PqrService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pqrs")
public class PqrController {

    private final PqrService pqrService;

    @PostMapping
    public ResponseEntity<Pqr> crearPqr(@RequestBody Pqr pqr) {
        return new ResponseEntity<>(pqrService.crearPqr(pqr), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pqr> obtenerPqrPorId(@PathVariable Integer id) {
        return pqrService.obtenerPqrPorId(id)
                .map(pqr -> new ResponseEntity<>(pqr, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Pqr> obtenerTodosPqrs() {
        return pqrService.obtenerTodosPqrs();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pqr> actualizarPqr(@PathVariable Integer id, @RequestBody Pqr pqrActualizado) {
        return new ResponseEntity<>(pqrService.actualizarPqr(id, pqrActualizado), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPqr(@PathVariable Integer id) {
        pqrService.eliminarPqr(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
