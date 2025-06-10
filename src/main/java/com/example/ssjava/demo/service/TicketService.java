package com.example.ssjava.demo.service;

import com.example.ssjava.demo.dto.MessageDTO;
import com.example.ssjava.demo.dto.TicketDTO;
import com.example.ssjava.demo.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
  List<Ticket> findAllTickets();
  Optional<Ticket> findTicketById(String id);
  TicketDTO createTicket(TicketDTO ticketDTO);
  void deleteTicket(String id);
  Ticket updateTicket(Ticket ticket);
  Ticket addMessageToTicket(String ticketId, MessageDTO messageDTO);
}