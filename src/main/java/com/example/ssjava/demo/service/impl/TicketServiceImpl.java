package com.example.ssjava.demo.service.impl;

import com.example.ssjava.demo.dto.MessageDTO;
import com.example.ssjava.demo.dto.TicketDTO;
import com.example.ssjava.demo.entity.Categoria;
import com.example.ssjava.demo.entity.Message;
import com.example.ssjava.demo.entity.Ticket;
import com.example.ssjava.demo.mapper.TicketMapper;
import com.example.ssjava.demo.repository.CategoriaRepository;
import com.example.ssjava.demo.repository.TicketRepository;
import com.example.ssjava.demo.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;
  private final CategoriaRepository categoriaRepository;
  private final TicketMapper ticketMapper;


  @Override
  public List<Ticket> findAllTickets() {
    return ticketRepository.findAll();
  }

  @Override
  public Optional<Ticket> findTicketById(String id) {
    return ticketRepository.findById(id);
  }

  public TicketDTO createTicket(TicketDTO ticketDTO) {

    Categoria categoria = categoriaRepository.findById(ticketDTO.getCategoryId())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

    Ticket ticket = ticketMapper.toEntityWithCategory(ticketDTO, categoria);

    ticket.setCreatedAt(LocalDateTime.now());
    getExpiration(categoria, ticket);

    return ticketMapper.toDTO(ticketRepository.save(ticket));
  }

  private static void getExpiration(Categoria categoria, Ticket ticket) {
    if (Objects.equals(categoria.getPriority(), "Alta")) {
      ticket.setExpiration(LocalDateTime.now().plusDays(1));
    } else if ("Media".equals(categoria.getPriority())) {
      ticket.setExpiration(LocalDateTime.now().plusDays(3));
    } else {
      ticket.setExpiration(LocalDateTime.now().plusDays(7));
    }
  }

  @Override
  public void deleteTicket(String id) {
    ticketRepository.deleteById(id);
  }

  @Override
  public Ticket updateTicket(Ticket ticket) {
    return ticketRepository.save(ticket);
  }

  @Override
  public Ticket addMessageToTicket(String ticketId, MessageDTO messageDTO) {
    Ticket optionalTicket = ticketRepository.findById(ticketId)
            .orElseThrow(() -> new RuntimeException("Ticket no encontrado con ID: " + ticketId));

    Message message = new Message();
    message.setAuthor(messageDTO.getAuthor());
    message.setText(messageDTO.getText());
    message.setInternal(messageDTO.getInternal());
    message.setTimestamp(LocalDateTime.now());

    optionalTicket.getMessages().add(message);

    return ticketRepository.save(optionalTicket);
  }
}