package br.com.natfrancisca.euvou.util;

import br.com.natfrancisca.euvou.model.Ticket;
import br.com.natfrancisca.euvou.model.Tickets;
import br.com.natfrancisca.euvou.repository.TicketRepository;
import br.com.natfrancisca.euvou.repository.TicketsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidatorTicket {
    final private TicketRepository ticketRepository;
    final private TicketsRepository ticketsRepository;

    @Autowired
    public ValidatorTicket(TicketRepository ticketRepository, TicketsRepository ticketsRepository){
        this.ticketRepository = ticketRepository;
        this.ticketsRepository = ticketsRepository;
    }

    public void validate(Ticket ticket, Tickets tickets){

        if(!this.ticketsRepository.existsByEventId(ticket.getEvent_id())){
            throw new EntityNotFoundException("Evento não cadastrou acesso aos ingressos.");
        }

        if(!tickets.getAccessStatus()){
            throw new IllegalStateException("O acesso aos ingressos foi bloqueado pelo organizador.");
        }

        if(this.ticketRepository.existsByClientId(ticket.getClient_id())){
            throw new DataIntegrityViolationException("Cliente já adquiriu ingresso para esse evento.");
        }

        if(LocalDateTime.now().isBefore(tickets.getDateAccessTickets())){
            throw new IllegalStateException("Acesso aos ingressos não está disponível ainda.");
        }

        LocalDateTime dateCloseAccessTickets = tickets.getDateAccessTickets().plusDays(tickets.getNumberDaysCloseAccessTickets());

        if(LocalDateTime.now().isAfter(dateCloseAccessTickets)){
            throw new IllegalStateException("Encerrado o acesso aos ingressos.");
        }

        if(this.ticketsRepository.countTicketsByEvent(ticket.getEvent_id()) == tickets.getAmount()){
            throw new IllegalStateException("Ingressos esgotados para esse evento.");
        }

        if(LocalDateTime.now().isAfter(tickets.getEvent().getDate())){
            throw new IllegalStateException("Esse evento já foi realizado.");
        }
    }
}
