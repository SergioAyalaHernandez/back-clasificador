package com.example.ssjava.demo.service;

import com.example.ssjava.demo.dto.CategoryStatsDto;
import com.example.ssjava.demo.dto.StatsDto;

import java.util.List;
import java.util.Map;

public interface InformeService {
  StatsDto getStats();
  List<Map<String, Object>> getFormattedTicketsByMonth();
  List<CategoryStatsDto> getCategoryStats();
}
