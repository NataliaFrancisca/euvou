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
    private final TicketsService ticketsService;

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

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDTO> getByID(@PathVariable Long id){
        return APIResponseDTO.create(HttpStatus.OK, this.ticketsService.getById(id));
    }

    @PutMapping("/{id}/manage-access")
    public ResponseEntity<APIResponseDTO> manageTicketAccess(@PathVariable Long id, @RequestParam boolean access){
        this.ticketsService.updateTicketAccess(id, access);
        return APIResponseDTO.create(HttpStatus.CREATED, access ? "Acesso aos ingressos liberado com sucesso." : "Acesso aos ingressos bloqueado com sucesso.");
    }
}
