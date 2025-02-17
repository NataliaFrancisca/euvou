package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.converter.EventConverter;
import br.com.natfrancisca.euvou.dto.EventDTO;
import br.com.natfrancisca.euvou.dto.OrganizerDTO;
import br.com.natfrancisca.euvou.dto.OrganizerShortDTO;
import br.com.natfrancisca.euvou.exception.ClientException;
import br.com.natfrancisca.euvou.model.Client;
import br.com.natfrancisca.euvou.model.Event;
import br.com.natfrancisca.euvou.model.Organizer;
import br.com.natfrancisca.euvou.repository.EventRepository;
import br.com.natfrancisca.euvou.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

@Service
public class EventService {
    final private EventRepository eventRepository;
    final private OrganizerRepository organizerRepository;

    @Autowired
    public EventService(EventRepository eventRepository,  OrganizerRepository organizerRepository){
        this.eventRepository = eventRepository;
        this.organizerRepository = organizerRepository;
    }

    public Event create(EventDTO eventDTO){
        if(eventDTO.getOrganizer_id() == null){
            throw new IllegalArgumentException("O cliente não pode ser nulo.");
        }

        Optional<Organizer> organizerOptional = this.organizerRepository.findById(eventDTO.getOrganizer_id());

        if(organizerOptional.isEmpty()){
            throw new ClientException("Não existe cliente com esse ID.");
        }

        Event event = eventDTO.toEntity();
        return this.eventRepository.save(event);
    }

    public List<EventDTO> list(){
        List<EventDTO> events = this.eventRepository.findAll().stream().map(EventConverter::convertToSummary).toList();
        return events;
    }

}
