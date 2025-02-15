package br.com.natfrancisca.euvou.controller;

import br.com.natfrancisca.euvou.dto.ClientDTO;
import br.com.natfrancisca.euvou.dto.ResponseDTO;
import br.com.natfrancisca.euvou.model.Client;
import br.com.natfrancisca.euvou.service.ClientService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody ClientDTO clientDTO){
        Client client = clientDTO.toEntity();
        this.clientService.create(client);
        return ResponseDTO.create(HttpStatus.CREATED, "Cliente cadastrado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        List<Client> clients = this.clientService.get();
        System.out.println(clients);
        return ResponseDTO.create(HttpStatus.OK, clients);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ResponseDTO> getByCPF(@Valid @PathVariable String cpf){
        Client client = clientService.getClient(cpf);
        return ResponseDTO.create(HttpStatus.OK, client);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ResponseDTO> update(@PathVariable String cpf, @Valid @RequestBody ClientDTO clientDTO){
        Client client = this.clientService.update(cpf, clientDTO.toEntity());
        return ResponseDTO.create(HttpStatus.OK, client);
    }

    @DeleteMapping("/{cpf}")
    @Transactional
    public ResponseEntity<ResponseDTO> delete(@PathVariable String cpf){
        this.clientService.delete(cpf);
        return ResponseDTO.create(HttpStatus.OK, "Cliente deletado com sucesso.");
    }
}
