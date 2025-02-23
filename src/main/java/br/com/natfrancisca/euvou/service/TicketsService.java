package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.exception.EventException;
import br.com.natfrancisca.euvou.exception.OrganizerException;
import br.com.natfrancisca.euvou.model.Tickets;
import br.com.natfrancisca.euvou.repository.TicketRepository;
import br.com.natfrancisca.euvou.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketsService {
    final private TicketsRepository ticketsRepository;

    @Autowired
    public TicketsService(TicketsRepository ticketsRepository){
        this.ticketsRepository = ticketsRepository;
    }

    public Tickets create(Tickets tickets){
        if(this.ticketsRepository.existsByEventId(tickets.getEvent_id())){
            throw new EventException("Ingressos para esse evento já foi cadastrado.");
        }

        return this.ticketsRepository.save(tickets);
    }

    public List<Tickets> getAll(){
        return this.ticketsRepository.findAll();
    }
}
