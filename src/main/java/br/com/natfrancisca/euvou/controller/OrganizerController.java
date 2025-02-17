package br.com.natfrancisca.euvou.controller;

import br.com.natfrancisca.euvou.dto.ResponseDTO;
import br.com.natfrancisca.euvou.model.Organizer;
import br.com.natfrancisca.euvou.service.OrganizerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService){
        this.organizerService = organizerService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody br.com.natfrancisca.euvou.dto.OrganizerDTO organizerDTO){
        Organizer responsible = organizerDTO.toEntity();
        this.organizerService.create(responsible);
        return ResponseDTO.create(HttpStatus.CREATED, "Organizador criado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> get(){
        return ResponseDTO.create(HttpStatus.OK, this.organizerService.getAll());
    }
}
