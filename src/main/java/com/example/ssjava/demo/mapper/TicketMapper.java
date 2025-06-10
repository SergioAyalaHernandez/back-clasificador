package com.example.ssjava.demo.mapper;

import com.example.ssjava.demo.dto.TicketDTO;
import com.example.ssjava.demo.entity.Categoria;
import com.example.ssjava.demo.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {})
public interface TicketMapper {

  TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

  @Mapping(target = "categoryId", source = "category.id")
  TicketDTO toDTO(Ticket ticket);

  @Mapping(target = "category", ignore = true)
  @Mapping(target = "messages", ignore = true)
  Ticket toEntity(TicketDTO ticketDTO);

  default Ticket toEntityWithCategory(TicketDTO ticketDTO, Categoria categoria) {
    Ticket ticket = toEntity(ticketDTO);
    ticket.setCategory(categoria);
    return ticket;
  }
}