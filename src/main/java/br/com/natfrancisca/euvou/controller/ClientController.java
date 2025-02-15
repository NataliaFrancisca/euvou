package br.com.natfrancisca.euvou.controller;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.natfrancisca.euvou.dto.ClientDTO;
import br.com.natfrancisca.euvou.dto.ResponseDTO;
import br.com.natfrancisca.euvou.model.Client;
import br.com.natfrancisca.euvou.service.ClientService;
import br.com.natfrancisca.euvou.util.CPFValidation;
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
        return ResponseDTO.create(HttpStatus.CREATED, "Sucesso", "Cliente cadastrado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        List<Client> clients = this.clientService.get();
        System.out.println(clients);
        return ResponseDTO.create(HttpStatus.OK, "Sucesso", clients);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ResponseDTO> getByCPF(@Valid @PathVariable String cpf){
        Client client = clientService.getClient(cpf);
        return ResponseDTO.create(HttpStatus.OK, "Sucesso", client);
    }
}
