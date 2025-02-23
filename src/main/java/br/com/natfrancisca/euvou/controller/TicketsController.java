package br.com.natfrancisca.euvou.controller;

import br.com.natfrancisca.euvou.dto.APIResponseDTO;
import br.com.natfrancisca.euvou.model.Tickets;
import br.com.natfrancisca.euvou.service.TicketService;
import br.com.natfrancisca.euvou.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketsController {
    private TicketsService ticketsService;

    @Autowired
    public TicketsController(TicketsService ticketsService){
        this.ticketsService = ticketsService;
    }

    @PostMapping
    public ResponseEntity<APIResponseDTO> create(@RequestBody Tickets tickets){
        Tickets ticketsNew = this.ticketsService.create(tickets);
        return APIResponseDTO.create(HttpStatus.CREATED, "Informações sobre ingressos cadastrado com sucesso.", ticketsNew);
    }

    @GetMapping
    public ResponseEntity<APIResponseDTO> get(){
        return APIResponseDTO.create(HttpStatus.OK, this.ticketsService.getAll());
    }
}
