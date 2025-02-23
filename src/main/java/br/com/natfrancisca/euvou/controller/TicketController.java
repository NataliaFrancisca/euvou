package br.com.natfrancisca.euvou.controller;

import br.com.natfrancisca.euvou.dto.APIResponseDTO;
import br.com.natfrancisca.euvou.model.Ticket;
import br.com.natfrancisca.euvou.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<APIResponseDTO> create(@RequestBody Ticket ticket){
        Ticket ticketNew = this.ticketService.create(ticket);
        return APIResponseDTO.create(HttpStatus.CREATED, "Ingresso emitido com sucesso", ticketNew);
    }

    @GetMapping
    public ResponseEntity<APIResponseDTO> get(){
        return APIResponseDTO.create(HttpStatus.OK, this.ticketService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDTO> getById(@PathVariable Long id){
        return APIResponseDTO.create(HttpStatus.OK, this.ticketService.get(id));
    }
}
