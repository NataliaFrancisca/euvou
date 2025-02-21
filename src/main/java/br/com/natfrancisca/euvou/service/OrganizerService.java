package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.exception.OrganizerException;
import br.com.natfrancisca.euvou.model.Organizer;
import br.com.natfrancisca.euvou.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizerService {
    final private OrganizerRepository organizerRepository;

    @Autowired
    public OrganizerService(OrganizerRepository organizerRepository){
        this.organizerRepository = organizerRepository;
    }

    private Organizer findOrganizerById(Long id){
        Optional<Organizer> organizerOptional = this.organizerRepository.findById(id);

        if(organizerOptional.isEmpty()){
            throw new OrganizerException("Nenhum organizador com esse ID.");
        }

        return organizerOptional.get();
    }

    public Organizer create(Organizer organizer){
        if(this.organizerRepository.existsByCnpj(organizer.getCnpj())){
            throw new OrganizerException("Já existe um cadastro com esse número de CNPJ.");
        }

        return this.organizerRepository.save(organizer);
    }

    public List<Organizer> getAll(){
        return this.organizerRepository.findAll();
    }

    public Organizer getById(Long id){
        return this.findOrganizerById(id);
    }

    public Organizer update(Long id, Organizer organizer){
        Organizer organizerToUpdate = this.findOrganizerById(id);

        organizerToUpdate.setName(organizer.getName());
        organizerToUpdate.setEmail(organizer.getEmail());
        organizerToUpdate.setCnpj(organizer.getCnpj());

        return this.organizerRepository.save(organizerToUpdate);
    }

    public void delete(Long id){
        this.findOrganizerById(id);
        this.organizerRepository.deleteById(id);
    }
}
