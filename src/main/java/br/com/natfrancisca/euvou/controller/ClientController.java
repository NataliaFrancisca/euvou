package br.com.natfrancisca.euvou.controller;

import br.com.natfrancisca.euvou.dto.ClientDTO;
import br.com.natfrancisca.euvou.dto.APIResponseDTO;
import br.com.natfrancisca.euvou.service.ClientService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<APIResponseDTO> create(@Valid @RequestBody ClientDTO clientDTO){
        this.clientService.create(clientDTO.toEntity());
        return APIResponseDTO.create(HttpStatus.CREATED, "Cliente cadastrado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<APIResponseDTO> getAll(){
        return APIResponseDTO.create(HttpStatus.OK, this.clientService.get());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<APIResponseDTO> getByCPF(@Valid @PathVariable String cpf){
        return APIResponseDTO.create(HttpStatus.OK, clientService.getByCPF(cpf));
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<APIResponseDTO> update(@PathVariable String cpf, @Valid @RequestBody ClientDTO clientDTO){
        return APIResponseDTO.create(HttpStatus.OK, this.clientService.update(cpf, clientDTO.toEntity()));
    }

    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<APIResponseDTO> delete(@PathVariable String cpf){
        this.clientService.delete(cpf);
        return APIResponseDTO.create(HttpStatus.OK, "Cliente removido com sucesso.");
    }
}
