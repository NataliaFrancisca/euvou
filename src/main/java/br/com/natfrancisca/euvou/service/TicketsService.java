package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.dto.TicketsDTO;
import br.com.natfrancisca.euvou.exception.EventException;
import br.com.natfrancisca.euvou.exception.OrganizerException;
import br.com.natfrancisca.euvou.model.Event;
import br.com.natfrancisca.euvou.model.Tickets;
import br.com.natfrancisca.euvou.repository.EventRepository;
import br.com.natfrancisca.euvou.repository.TicketRepository;
import br.com.natfrancisca.euvou.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketsService {
    final private TicketsRepository ticketsRepository;
    final private EventRepository eventRepository;

    @Autowired
    public TicketsService(TicketsRepository ticketsRepository, EventRepository eventRepository){
        this.ticketsRepository = ticketsRepository;
        this.eventRepository = eventRepository;
    }

    public Tickets create(Tickets tickets){
        Optional<Event> event = this.eventRepository.findById(tickets.getEvent_id());

        if(event.isEmpty()){
            throw new EventException("Ingressos para esse evento já foi cadastrado.");
        }

        if(event.get().getDate().isBefore(tickets.getDateAccessTickets())){
            throw new IllegalArgumentException("Data de acesso aos ingressos deve ser antes da data do evento.");
        }

        tickets.setEvent(event.get());
        return this.ticketsRepository.save(tickets);
    }

    public List<TicketsDTO> getAll(){
        return this.ticketsRepository.findAll().stream().map(TicketsDTO::fromEntity).toList();
    }
}
