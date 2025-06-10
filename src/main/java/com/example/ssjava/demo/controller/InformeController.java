package com.example.ssjava.demo.controller;

import com.example.ssjava.demo.dto.CategoryStatsDto;
import com.example.ssjava.demo.dto.StatsDto;
import com.example.ssjava.demo.service.InformeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/informe")
@AllArgsConstructor
@CrossOrigin("*")
public class InformeController {

  private final InformeService informeService;

  @GetMapping("/stats")
  public StatsDto getStats() {
    return informeService.getStats();
  }

  @GetMapping("/tickets-por-mes")
  public ResponseEntity<List<Map<String, Object>>> obtenerTicketsPorMes() {
    return ResponseEntity.ok(informeService.getFormattedTicketsByMonth());
  }

  @GetMapping("/stats/categories")
  public ResponseEntity<Map<String, Object>> getCategoryStats() {
    List<CategoryStatsDto> stats = informeService.getCategoryStats();

    Map<String, Object> response = new HashMap<>();
    response.put("categoryStats", stats);

    return ResponseEntity.ok(response);
  }
}
