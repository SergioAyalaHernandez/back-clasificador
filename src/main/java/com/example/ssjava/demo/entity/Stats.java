package com.example.ssjava.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stats")
public class Stats {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "total_users", nullable = false)
  private Integer totalUsers;

  @Column(name = "open_tickets", nullable = false)
  private Integer openTickets;

  @Column(name = "resolved_tickets", nullable = false)
  private Integer resolvedTickets;

  @Column(name = "avg_resolution_time", nullable = false)
  private Double avgResolutionTime;

  @Column(name = "monthly_sales", nullable = false)
  private Integer monthlySales;

  @Column(name = "expired_tickets", nullable = false)
  private Integer expiredTickets;
}
