package br.com.natfrancisca.euvou.controller;

import br.com.natfrancisca.euvou.dto.APIResponseDTO;
import br.com.natfrancisca.euvou.dto.OrganizerDTO;
import br.com.natfrancisca.euvou.service.OrganizerService;
import jakarta.transaction.Transactional;
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
        this.organizerService.create(organizerDTO.toEntity());
        return APIResponseDTO.create(HttpStatus.CREATED, "Organização cadastrada com sucesso.");
    }

    @GetMapping
    public ResponseEntity<APIResponseDTO> get(){
        return APIResponseDTO.create(HttpStatus.OK, this.organizerService.get());
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<APIResponseDTO> getByCNPJ(@PathVariable String cnpj){
        return APIResponseDTO.create(HttpStatus.OK, this.organizerService.getByCNPJ(cnpj));
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<APIResponseDTO> update(@PathVariable String cnpj, @Valid @RequestBody OrganizerDTO organizerDTO){
        return APIResponseDTO.create(HttpStatus.OK, this.organizerService.update(cnpj, organizerDTO.toEntity()));
    }

    @DeleteMapping("/{cnpj}")
    @Transactional
    public ResponseEntity<APIResponseDTO> delete(@PathVariable String cnpj){
        this.organizerService.delete(cnpj);
        return APIResponseDTO.create(HttpStatus.OK, "Organização deletada com sucesso.");
    }
}
