package com.example.ssjava.demo.controller;

import com.example.ssjava.demo.entity.Area;
import com.example.ssjava.demo.service.AreaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
@AllArgsConstructor
public class AreaController {

  private final AreaService areaService;

  @GetMapping
  public ResponseEntity<List<Area>> getAllAreas() {
    List<Area> areas = areaService.findAllAreas();
    return new ResponseEntity<>(areas, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Area> getAreaById(@PathVariable Integer id) {
    return areaService.findAreaById(id)
            .map(area -> new ResponseEntity<>(area, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<Area> createArea(@RequestBody Area area) {
    Area savedArea = areaService.createArea(area);
    return new ResponseEntity<>(savedArea, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Area> updateArea(@PathVariable Integer id, @RequestBody Area area) {
    return areaService.findAreaById(id)
            .map(existingArea -> {
              area.setId(id);
              Area updatedArea = areaService.updateArea(area);
              return new ResponseEntity<>(updatedArea, HttpStatus.OK);
            })
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteArea(@PathVariable Integer id) {
    return areaService.findAreaById(id)
            .map(area -> {
              areaService.deleteArea(id);
              return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            })
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}