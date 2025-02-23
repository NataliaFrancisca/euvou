package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.dto.TicketDTO;
import br.com.natfrancisca.euvou.exception.ClientException;
import br.com.natfrancisca.euvou.model.Ticket;
import br.com.natfrancisca.euvou.model.Tickets;
import br.com.natfrancisca.euvou.repository.TicketRepository;
import br.com.natfrancisca.euvou.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    final private TicketRepository ticketRepository;
    final private TicketsRepository ticketsRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository, TicketsRepository ticketsRepository){
        this.ticketRepository = ticketRepository;
        this.ticketsRepository = ticketsRepository;
    }

    public Ticket create(Ticket ticket){

        if(!this.ticketsRepository.existsByEventId(ticket.getEvent_id())){
            throw new IllegalArgumentException("Evento não disponibilizou ingressos.");
        }

        if(this.ticketRepository.existsByClientId(ticket.getClient_id())){
            throw new ClientException("Cliente já adquiriu ingresso para esse evento.");
        }

        Tickets tickets = ticketsRepository.getByEventId(ticket.getEvent_id());

        if(LocalDateTime.now().isBefore(tickets.getDateAccessTickets())){
            throw new IllegalArgumentException("Acesso aos ingressos não está disponível ainda.");
        }

        if(this.ticketsRepository.countTicketsByEvent(ticket.getEvent_id()) == tickets.getAmount()){
            throw new IllegalArgumentException("Ingressos esgotados.");
        }

        ticket.setTickets(tickets);

        return this.ticketRepository.save(ticket);
    }

    public List<TicketDTO> getAll(){
        System.out.println(this.ticketRepository.findAll());
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
