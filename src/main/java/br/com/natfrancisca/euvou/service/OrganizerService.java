package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.model.Organizer;
import br.com.natfrancisca.euvou.repository.OrganizerRepository;
import br.com.natfrancisca.euvou.util.ValidatorCNPJ;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {
    final private OrganizerRepository organizerRepository;
    final private ValidatorCNPJ validatorCNPJ;

    @Autowired
    public OrganizerService(OrganizerRepository organizerRepository, ValidatorCNPJ validatorCNPJ){
        this.organizerRepository = organizerRepository;
        this.validatorCNPJ = validatorCNPJ;
    }

    private String convertStringToCNPJ(String cnpj){
        return cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }

    private Organizer getOrganizerOrCheckOrganizerExist(String cnpj){
        Optional<Organizer> organizerOptional = this.organizerRepository.findOrganizerByCnpj(cnpj);

        if(organizerOptional.isEmpty()){
            throw new EntityNotFoundException("Não existe registro de organização com esse CNPJ.");
        }

        return organizerOptional.get();
    }

    public Organizer create(Organizer organizer){
        this.validatorCNPJ.validate(organizer.getCnpj());

        if(this.organizerRepository.existsByCnpj(organizer.getCnpj())){
            throw new DataIntegrityViolationException("Já existe uma organização registrada com esse CNPJ.");
        }

        if(this.organizerRepository.existsByEmail(organizer.getEmail())){
            throw new DataIntegrityViolationException("Já existe uma organização registrada com esse endereço de E-mail.");
        }

        return this.organizerRepository.save(organizer);
    }

    public List<Organizer> get(){
        return this.organizerRepository.findAll();
    }

    public Organizer getByCNPJ(String cnpj){
        String cnpjFormatted = convertStringToCNPJ(cnpj);
        this.validatorCNPJ.validate(cnpjFormatted);
        return this.getOrganizerOrCheckOrganizerExist(cnpjFormatted);
    }

    public Organizer update(String cnpj, Organizer organizer){
        String cnpjFormatted = convertStringToCNPJ(cnpj);
        this.validatorCNPJ.validate(cnpjFormatted);

        if(!cnpjFormatted.equals(organizer.getCnpj()) && this.organizerRepository.existsByCnpj(organizer.getCnpj())){
            throw new DataIntegrityViolationException("Já existe uma organização registrada com esse CNPJ.");
        }

        Organizer organizerToUpdate = this.getOrganizerOrCheckOrganizerExist(cnpjFormatted);

        this.validatorCNPJ.validate(organizer.getCnpj());

        organizerToUpdate.setName(organizer.getName());
        organizerToUpdate.setEmail(organizer.getEmail());
        organizerToUpdate.setCnpj(organizer.getCnpj());

        return this.organizerRepository.save(organizerToUpdate);
    }

    public void delete(String cnpj){
        String cnpjFormatted = convertStringToCNPJ(cnpj);
        this.validatorCNPJ.validate(cnpjFormatted);
        this.getOrganizerOrCheckOrganizerExist(cnpjFormatted);

        this.organizerRepository.deleteByCnpj(cnpjFormatted);
    }

}
