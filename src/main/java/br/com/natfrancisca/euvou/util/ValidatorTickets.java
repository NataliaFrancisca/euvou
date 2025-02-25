package br.com.natfrancisca.euvou.util;

import br.com.natfrancisca.euvou.model.Event;
import br.com.natfrancisca.euvou.model.Tickets;
import br.com.natfrancisca.euvou.repository.EventRepository;
import br.com.natfrancisca.euvou.repository.TicketsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidatorTickets {
    final private TicketsRepository ticketsRepository;
    final private EventRepository eventRepository;

    @Autowired
    public ValidatorTickets(TicketsRepository ticketsRepository, EventRepository eventRepository){
        this.ticketsRepository = ticketsRepository;
        this.eventRepository = eventRepository;
    }

    public Event validate(Tickets tickets){
        Optional<Event> eventOptional = this.eventRepository.findById(tickets.getEvent_id());

        if(this.ticketsRepository.existsByEventId(tickets.getEvent_id())){
            throw new DataIntegrityViolationException("Evento já cadastrou informações sobre acesso aos ingressos.");
        }

        if(eventOptional.isEmpty()){
            throw new EntityNotFoundException("Não encontramos evento com esse ID.");
        }

        if(eventOptional.get().getDate().isBefore(tickets.getDateAccessTickets())){
            throw new IllegalArgumentException("A data para liberar ingressos deve ser antes da data do evento.");
        }

        if(eventOptional.get().getOrganizer() == null){
            throw new IllegalStateException("Para liberar acesso aos ingressos, é necessário que o evento tenha um organizador.");
        }

        return eventOptional.get();
    }
}
