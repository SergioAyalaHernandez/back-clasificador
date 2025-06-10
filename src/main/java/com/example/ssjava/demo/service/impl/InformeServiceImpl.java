package com.example.ssjava.demo.service.impl;

import com.example.ssjava.demo.dto.CategoryStatsDto;
import com.example.ssjava.demo.dto.StatsDto;
import com.example.ssjava.demo.repository.TicketRepository;
import com.example.ssjava.demo.repository.UserRepository;
import com.example.ssjava.demo.service.InformeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class InformeServiceImpl implements InformeService {

  private final UserRepository userRepository;
  private final TicketRepository ticketRepository;

  @Override
  public StatsDto getStats() {
    StatsDto stats = new StatsDto();
    stats.setTotalUsers(userRepository.countTotalUsers());
    stats.setOpenTickets(ticketRepository.countPendingTickets());
    stats.setExpiredTickets(ticketRepository.countExpiredTickets());
    stats.setResolvedTickets(ticketRepository.countResolvedTickets());
    stats.setAvgResolutionTime(10L);
    return stats;
  }

  @Override
  public List<Map<String, Object>> getFormattedTicketsByMonth() {
    List<Object[]> rawData = ticketRepository.getTicketsByMonthAndCategory();
    Map<String, Map<String, Object>> resultsByMonth = new HashMap<>();

    for (Object[] row : rawData) {
      String month = (String) row[2];
      String category = (String) row[3];
      Long total = ((Number) row[4]).longValue();

      resultsByMonth.computeIfAbsent(month, k -> {
        Map<String, Object> monthData = new HashMap<>();
        monthData.put("month", month);
        monthData.put("id", "a" + UUID.randomUUID().toString().substring(0, 3));
        return monthData;
      }).put(category, total);
    }

    return new ArrayList<>(resultsByMonth.values());
  }

  @Override
  public List<CategoryStatsDto> getCategoryStats() {
    return ticketRepository.getCategoryStats();
  }

}
