package br.com.natfrancisca.euvou.controller;

import br.com.natfrancisca.euvou.dto.APIResponseDTO;
import br.com.natfrancisca.euvou.dto.TicketsDTO;
import br.com.natfrancisca.euvou.service.TicketsService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    public ResponseEntity<APIResponseDTO> create(@RequestBody @Valid TicketsDTO ticketsDTO){
        this.ticketsService.create(ticketsDTO.toEntity());
        return APIResponseDTO.create(HttpStatus.CREATED, "Informações sobre ingressos cadastrado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<APIResponseDTO> get(){
        return APIResponseDTO.create(HttpStatus.OK, this.ticketsService.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDTO> getById(@PathVariable Long id){
        return APIResponseDTO.create(HttpStatus.OK, this.ticketsService.getById(id));
    }

    @PutMapping("/{id}/manage-access")
    public ResponseEntity<APIResponseDTO> updateAccess(@PathVariable Long id, @RequestParam  boolean access){
        this.ticketsService.updateAccess(id, access);
        return APIResponseDTO.create(HttpStatus.OK, access ? "Acesso aos ingressos liberado com sucesso." : "Acesso aos ingressos bloqueado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponseDTO> update(@PathVariable Long id, @RequestBody TicketsDTO ticketsDTO){
        return APIResponseDTO.create(HttpStatus.OK, this.ticketsService.update(id, ticketsDTO.toEntity()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<APIResponseDTO> delete(@PathVariable Long id){
        this.ticketsService.delete(id);
        return APIResponseDTO.create(HttpStatus.OK, "Informações sobre ingressos deletado com sucesso.");
    }
}
