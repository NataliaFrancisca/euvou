package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.dto.EventDTO;
import br.com.natfrancisca.euvou.exception.OrganizerException;
import br.com.natfrancisca.euvou.model.Event;
import br.com.natfrancisca.euvou.model.Organizer;
import br.com.natfrancisca.euvou.repository.EventRepository;
import br.com.natfrancisca.euvou.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    final private EventRepository eventRepository;
    final private OrganizerRepository organizerRepository;

    @Autowired
    public EventService(EventRepository eventRepository,  OrganizerRepository organizerRepository){
        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
    }

    public Event create(Event event){
        if(event.getOrganizer_id() == null){
            throw new OrganizerException("O organizador do evento não pode ser nulo.");
        }

        Optional<Organizer> organizerOptional = this.organizerRepository.findById(event.getOrganizer_id());

        if(organizerOptional.isEmpty()){
            throw new OrganizerException("Não existe nenhum organizador com esse ID.");
        }

        return this.eventRepository.save(event);
    }

    public List<EventDTO> list(){
        return this.eventRepository.findAll()
                .stream()
                .map(EventDTO::fromEntity)
                .toList();
    }

}
