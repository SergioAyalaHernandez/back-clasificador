package com.example.ssjava.demo.repository;

import com.example.ssjava.demo.dto.CategoryStatsDto;
import com.example.ssjava.demo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
  @Query(value = "SELECT COUNT(*) FROM tickets WHERE status = 'Resuelto'", nativeQuery = true)
  Long countResolvedTickets();

  @Query(value = "SELECT COUNT(*) FROM tickets WHERE status = 'Pendiente'", nativeQuery = true)
  Long countPendingTickets();

  @Query(value = "SELECT COUNT(*) FROM tickets WHERE status = 'Pendiente' AND expiration < CURRENT_TIMESTAMP", nativeQuery = true)
  Long countExpiredTickets();

  @Query(value = "SELECT " +
          "TO_CHAR(t.created_at, 'YYYY-MM') AS year_month, " +
          "TO_CHAR(t.created_at, 'MM') AS mes_num, " +
          "CASE TO_CHAR(t.created_at, 'MM') " +
          "    WHEN '01' THEN 'Enero' " +
          "    WHEN '02' THEN 'Febrero' " +
          "    WHEN '03' THEN 'Marzo' " +
          "    WHEN '04' THEN 'Abril' " +
          "    WHEN '05' THEN 'Mayo' " +
          "    WHEN '06' THEN 'Junio' " +
          "    WHEN '07' THEN 'Julio' " +
          "    WHEN '08' THEN 'Agosto' " +
          "    WHEN '09' THEN 'Septiembre' " +
          "    WHEN '10' THEN 'Octubre' " +
          "    WHEN '11' THEN 'Noviembre' " +
          "    WHEN '12' THEN 'Diciembre' " +
          "END AS month, " +
          "c.name AS category, " +
          "COUNT(*) AS total " +
          "FROM tickets t " +
          "LEFT JOIN categorias c ON c.id = t.category_id " +
          "GROUP BY year_month, mes_num, month, c.name " +
          "ORDER BY year_month, category", nativeQuery = true)
  List<Object[]> getTicketsByMonthAndCategory();

  @Query(value = "SELECT " +
          "c.name AS category, " +
          "COUNT(*) FILTER (WHERE t.status ILIKE 'Pendiente') AS open, " +
          "COUNT(*) FILTER (WHERE t.status ILIKE 'Resuelto') AS resolved, " +
          "SUBSTRING(MD5(c.name) FOR 4) AS id " +
          "FROM tickets t " +
          "LEFT JOIN categorias c ON c.id = t.category_id " +
          "GROUP BY c.name " +
          "ORDER BY c.name", nativeQuery = true)
  List<CategoryStatsDto> getCategoryStats();
}