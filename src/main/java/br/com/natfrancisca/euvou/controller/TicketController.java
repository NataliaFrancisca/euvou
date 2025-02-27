package br.com.natfrancisca.euvou.controller;

import br.com.natfrancisca.euvou.dto.APIResponseDTO;
import br.com.natfrancisca.euvou.model.Ticket;
import br.com.natfrancisca.euvou.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return APIResponseDTO.create(HttpStatus.CREATED, "Ingresso emitido com sucesso", this.ticketService.create(ticket));
    }

    @GetMapping
    public ResponseEntity<APIResponseDTO> get(){
        return APIResponseDTO.create(HttpStatus.OK, this.ticketService.get());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<APIResponseDTO> getByCPF(@PathVariable String cpf){
        return APIResponseDTO.create(HttpStatus.OK, this.ticketService.getByCPF(cpf));
    }


}
