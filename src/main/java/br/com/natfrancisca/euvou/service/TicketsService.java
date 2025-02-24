package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.dto.TicketsDTO;
import br.com.natfrancisca.euvou.exception.EventException;
import br.com.natfrancisca.euvou.exception.TicketsException;
import br.com.natfrancisca.euvou.model.Event;
import br.com.natfrancisca.euvou.model.Tickets;
import br.com.natfrancisca.euvou.repository.EventRepository;
import br.com.natfrancisca.euvou.repository.TicketsRepository;
import jakarta.persistence.EntityNotFoundException;
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
        if(this.ticketsRepository.existsByEventId(tickets.getEvent_id())){
            throw new EventException("Evento já cadastrou acesso aos ingressos.");
        }

        Optional<Event> event = this.eventRepository.findById(tickets.getEvent_id());

        if(event.isEmpty()){
            throw new EventException("Não existe evento com esse ID.");
        }

        if(event.get().getDate().isBefore(tickets.getDateAccessTickets())){
            throw new TicketsException("Data de acesso aos ingressos deve ser antes da data do evento.");
        }

        tickets.setEvent(event.get());
        return this.ticketsRepository.save(tickets);
    }

    public TicketsDTO getById(Long id){
        Optional<Tickets> ticketsOptional = this.ticketsRepository.findById(id);

        if(ticketsOptional.isEmpty()){
            throw new EntityNotFoundException("Não há ingressos cadastrados para esse evento.");
        }

        return TicketsDTO.fromEntity(ticketsOptional.get());
    }

    public List<TicketsDTO> getAll(){
        return this.ticketsRepository.findAll().stream().map(TicketsDTO::fromEntity).toList();
    }

    public void updateTicketAccess(Long id, boolean status){
        Optional<Tickets> ticketsOptional = this.ticketsRepository.findById(id);

        if(ticketsOptional.isEmpty()){
            throw new EntityNotFoundException("Não há ingressos cadastrados para esse evento.");
        }

        Tickets tickets = ticketsOptional.get();

        if(tickets.isAccessStatus() == status){
            throw new TicketsException("Você está tentando salvar o mesmo dado.");
        }

        tickets.setAccessStatus(status);
        this.ticketsRepository.save(tickets);
    }
}
