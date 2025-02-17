package br.com.natfrancisca.euvou.controller;

import br.com.natfrancisca.euvou.converter.EventConverter;
import br.com.natfrancisca.euvou.dto.EventDTO;
import br.com.natfrancisca.euvou.dto.ResponseDTO;
import br.com.natfrancisca.euvou.model.Event;
import br.com.natfrancisca.euvou.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody EventDTO event){
        this.eventService.create(event);
        return ResponseDTO.create(HttpStatus.CREATED, "Evento cadastrado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        List<EventDTO> events = this.eventService.list();
        return ResponseDTO.create(HttpStatus.OK, events);
    }
}
