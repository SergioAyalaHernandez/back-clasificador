package com.example.ssjava.demo.dto;

import lombok.Data;

@Data
public class StatsDto {
  private Long totalUsers;
  private Long openTickets;
  private Long resolvedTickets;
  private Long avgResolutionTime;
  private Long expiredTickets;
}
