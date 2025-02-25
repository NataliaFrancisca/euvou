package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.dto.TicketsDTO;
import br.com.natfrancisca.euvou.model.Event;
import br.com.natfrancisca.euvou.model.Tickets;
import br.com.natfrancisca.euvou.repository.TicketsRepository;
import br.com.natfrancisca.euvou.util.ValidatorTickets;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketsService {
    final private TicketsRepository ticketsRepository;
    final private ValidatorTickets validatorTickets;

    @Autowired
    public TicketsService(TicketsRepository ticketsRepository, ValidatorTickets validatorTickets){
        this.ticketsRepository = ticketsRepository;
        this.validatorTickets = validatorTickets;
    }

    private Tickets getTicketsOrCheckTicketsExist(Long id){
        Optional<Tickets> optionalTickets = this.ticketsRepository.findByEventId(id);

        if(optionalTickets.isEmpty()){
            throw new EntityNotFoundException("Não há ingressos cadastrados para esse evento.");
        }

        return optionalTickets.get();
    }

    public Tickets create(Tickets tickets){
        Event event = this.validatorTickets.validate(tickets);
        tickets.setEvent(event);
        return this.ticketsRepository.save(tickets);
    }

    public List<TicketsDTO> get(){
        return this.ticketsRepository.findAll().stream().map(TicketsDTO::fromEntity).toList();
    }

    public Tickets getById(Long id){
       return this.getTicketsOrCheckTicketsExist(id);
    }

    public Tickets update(Long id, Tickets tickets){
        Tickets ticketsToUpdate = this.getTicketsOrCheckTicketsExist(id);

        if(!tickets.getEvent_id().equals(ticketsToUpdate.getEvent_id()) && this.ticketsRepository.existsByEventId(tickets.getEvent_id())) {
            throw new DataIntegrityViolationException("Já existe ingressos para esse evento.");
        }

        ticketsToUpdate.setAmount(tickets.getAmount());
        ticketsToUpdate.setNumberDaysCloseAccessTickets(tickets.getNumberDaysCloseAccessTickets());
        ticketsToUpdate.setEvent_id(tickets.getEvent_id());
        ticketsToUpdate.setDateAccessTickets(tickets.getDateAccessTickets());
        ticketsToUpdate.setAccessStatus(tickets.isAccessStatus());

        return this.ticketsRepository.save(ticketsToUpdate);
    }

    public void updateAccess(Long id, boolean status){
        Tickets tickets = this.getTicketsOrCheckTicketsExist(id);

        if(tickets.isAccessStatus() == status){
            throw new IllegalStateException("Você está tentando salvar o mesmo dado.");
        }

        tickets.setAccessStatus(status);
        this.ticketsRepository.save(tickets);
    }

    public void delete(Long id){
        Tickets tickets = this.getTicketsOrCheckTicketsExist(id);
        this.ticketsRepository.deleteById(tickets.getId());
    }
}
