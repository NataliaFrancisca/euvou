package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.dto.TicketDTO;
import br.com.natfrancisca.euvou.model.Ticket;
import br.com.natfrancisca.euvou.model.Tickets;
import br.com.natfrancisca.euvou.repository.TicketRepository;
import br.com.natfrancisca.euvou.repository.TicketsRepository;
import br.com.natfrancisca.euvou.util.ValidatorTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    final private TicketRepository ticketRepository;
    final private TicketsRepository ticketsRepository;
    final private ValidatorTicket ticketValidator;

    @Autowired
    public TicketService(TicketRepository ticketRepository, TicketsRepository ticketsRepository, ValidatorTicket ticketValidator){
        this.ticketRepository = ticketRepository;
        this.ticketsRepository = ticketsRepository;
        this.ticketValidator = ticketValidator;
    }

    public Ticket create(Ticket ticket){
        Tickets tickets = ticketsRepository.getByEventId(ticket.getEvent_id());

        ticketValidator.validate(ticket, tickets);
        ticket.setTickets(tickets);

        return this.ticketRepository.save(ticket);
    }

    public List<TicketDTO> getAll(){
        return this.ticketRepository.findAll().stream().map(TicketDTO::fromEntity).toList();
    }

    public TicketDTO get(Long id){
        Optional<Ticket> ticketDTOOptional = this.ticketRepository.findById(id);

        if(ticketDTOOptional.isEmpty()){
            throw new IllegalArgumentException("Não existe nenhum ingresso com esse ID.");
        }

        return TicketDTO.fromEntity(ticketDTOOptional.get());
    }
}
