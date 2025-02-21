package br.com.natfrancisca.euvou.controller;

import br.com.natfrancisca.euvou.dto.APIResponseDTO;
import br.com.natfrancisca.euvou.dto.OrganizerDTO;
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
    public ResponseEntity<APIResponseDTO> create(@Valid @RequestBody OrganizerDTO organizerDTO){
        Organizer responsible = organizerDTO.toEntity();
        this.organizerService.create(responsible);
        return APIResponseDTO.create(HttpStatus.CREATED, "Organizador criado com sucesso.");
    }

    @GetMapping
    public ResponseEntity<APIResponseDTO> get(){
        return APIResponseDTO.create(HttpStatus.OK, this.organizerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseDTO> getById(@PathVariable Long id){
        return APIResponseDTO.create(HttpStatus.OK, this.organizerService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponseDTO> update(@PathVariable Long id, @RequestBody OrganizerDTO organizerDTO){
        Organizer organizer = organizerDTO.toEntity();
        Organizer organizerUpdated = this.organizerService.update(id, organizer);
        return APIResponseDTO.create(HttpStatus.OK, "Valores atualizado com sucesso.", organizerUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponseDTO> delete(@PathVariable Long id){
        this.organizerService.delete(id);
        return APIResponseDTO.create(HttpStatus.OK, "Organização deletada com sucesso");
    }
}
