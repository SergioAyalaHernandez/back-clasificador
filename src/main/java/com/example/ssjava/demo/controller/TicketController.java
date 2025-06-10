package com.example.ssjava.demo.controller;

import com.example.ssjava.demo.dto.MessageDTO;
import com.example.ssjava.demo.dto.TicketDTO;
import com.example.ssjava.demo.entity.Ticket;
import com.example.ssjava.demo.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {

  private final TicketService ticketService;

  @GetMapping
  public ResponseEntity<List<Ticket>> getAllTickets() {
    List<Ticket> tickets = ticketService.findAllTickets();
    return new ResponseEntity<>(tickets, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Ticket> getTicketById(@PathVariable String id) {
    return ticketService.findTicketById(id)
            .map(ticket -> new ResponseEntity<>(ticket, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDTO) {
    TicketDTO savedTicket = ticketService.createTicket(ticketDTO);
    return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Ticket> updateTicket(@PathVariable String id, @RequestBody Ticket ticket) {
    return ticketService.findTicketById(id)
            .map(existingTicket -> {
              ticket.setId(Long.valueOf(id));
              Ticket updatedTicket = ticketService.updateTicket(ticket);
              return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
            })
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTicket(@PathVariable String id) {
    return ticketService.findTicketById(id)
            .map(ticket -> {
              ticketService.deleteTicket(id);
              return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            })
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/{ticketId}/messages")
  public ResponseEntity<Ticket> addMessageToTicket(
          @PathVariable String ticketId,
          @RequestBody MessageDTO messageDTO) {

    try {
      Ticket updatedTicket = ticketService.addMessageToTicket(ticketId, messageDTO);
      return ResponseEntity.ok(updatedTicket);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }
}