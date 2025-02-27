package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.dto.TicketDTO;
import br.com.natfrancisca.euvou.model.Ticket;
import br.com.natfrancisca.euvou.model.Tickets;
import br.com.natfrancisca.euvou.repository.TicketRepository;
import br.com.natfrancisca.euvou.repository.TicketsRepository;
import br.com.natfrancisca.euvou.util.ValidatorCPF;
import br.com.natfrancisca.euvou.util.ValidatorTicket;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    final private TicketRepository ticketRepository;
    final private TicketsRepository ticketsRepository;
    final private ValidatorTicket ticketValidator;
    final private ValidatorCPF validatorCPF;

    @Autowired
    public TicketService(TicketRepository ticketRepository, TicketsRepository ticketsRepository, ValidatorTicket ticketValidator, ValidatorCPF validatorCPF){
        this.ticketRepository = ticketRepository;
        this.ticketsRepository = ticketsRepository;
        this.ticketValidator = ticketValidator;
        this.validatorCPF = validatorCPF;
    }

    private String convertStringToCPF(String cpf){
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    public TicketDTO create(Ticket ticket){

        Optional<Tickets> tickets = ticketsRepository.findByEventId(ticket.getEvent_id());

        if(tickets.isEmpty()){
            throw new EntityNotFoundException("Não encontramos ingressos para esse evento.");
        }

        ticketValidator.validate(ticket, tickets.get());
        return TicketDTO.fromEntity(this.ticketRepository.save(ticket));
    }

    public List<TicketDTO> get(){
        return this.ticketRepository.findAll().stream().map(TicketDTO::fromEntity).toList();
    }

    public TicketDTO getByCPF(String cpf){
        String cpfFormatted = convertStringToCPF(cpf);
        this.validatorCPF.validate(cpfFormatted);

        Optional<Ticket> ticketOptional = this.ticketRepository.findByClientCpf(cpfFormatted);

        if(ticketOptional.isEmpty()){
            throw new EntityNotFoundException("Não existe ingresso com esse número de CPF.");
        }

        return TicketDTO.fromEntity(ticketOptional.get());
    }
}
