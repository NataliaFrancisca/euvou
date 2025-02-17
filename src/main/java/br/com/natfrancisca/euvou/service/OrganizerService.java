package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.model.Organizer;
import br.com.natfrancisca.euvou.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerService {
    final private OrganizerRepository organizerRepository;

    @Autowired
    public OrganizerService(OrganizerRepository organizerRepository){
        this.organizerRepository = organizerRepository;
    }

    public Organizer create(Organizer responsible){
        return this.organizerRepository.save(responsible);
    }

    public List<Organizer> getAll(){
        return this.organizerRepository.findAll();
    }

}
