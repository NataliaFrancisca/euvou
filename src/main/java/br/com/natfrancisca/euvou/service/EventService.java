package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.dto.EventDTO;
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
            throw new IllegalArgumentException("O organizador do evento não pode ser nulo.");
        }

        Optional<Organizer> organizerOptional = this.organizerRepository.findById(event.getOrganizer_id());

        if(organizerOptional.isEmpty()){
            throw new IllegalStateException("Não existe nenhum organizador com esse ID.");
        }

        return this.eventRepository.save(event);
    }

    public List<EventDTO> list(){
        return this.eventRepository.findAll()
                .stream()
                .map(EventDTO::fromEntity)
                .toList();
    }

    public EventDTO get(Long id){
        Optional<Event> eventOptional = this.eventRepository.findById(id);

        return eventOptional.map(EventDTO::fromEntity)
                .orElseThrow(() -> new IllegalStateException("Nenhum evento encontrado com esse ID."));
    }

    public List<EventDTO> getByName(String title){
        List<Event> events = this.eventRepository.findByNameContainingIgnoreCase(title);

        if(events.isEmpty()){
            throw new IllegalStateException("Nenhum evento com esse nome.");
        }

        return events.stream().map(EventDTO::fromEntity).toList();
    }

    public void delete(Long id){
        Optional<Event> optionalEvent = this.eventRepository.findById(id);

        if(optionalEvent.isEmpty()){
            throw new IllegalStateException("Nenhum evento encontrado com esse ID.");
        }

        this.eventRepository.delete(optionalEvent.get());
    }

    public Event update(Long id, Event event){
        Optional<Event> optionalEvent = this.eventRepository.findById(id);

        if(optionalEvent.isEmpty()){
            throw new IllegalStateException("Nenhum evento encontrado com esse ID.");
        }

        Optional<Organizer> organizerOptional = this.organizerRepository.findById(event.getOrganizer_id());

        if(organizerOptional.isEmpty()){
            throw new IllegalStateException("Não existe nenhum organizador com esse ID.");
        }

        Event eventToUpdate = optionalEvent.get();

        eventToUpdate.setName(event.getName());
        eventToUpdate.setAddress(event.getAddress());
        eventToUpdate.setOrganizer_id(event.getOrganizer_id());
        eventToUpdate.setOrganizer(organizerOptional.get());

        return this.eventRepository.save(eventToUpdate);
    }

}
