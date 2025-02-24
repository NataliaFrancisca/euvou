package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.model.Client;
import br.com.natfrancisca.euvou.repository.ClientRepository;
import br.com.natfrancisca.euvou.util.ValidatorCPF;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    final private ClientRepository clientRepository;
    final private ValidatorCPF validatorCPF;

    @Autowired
    public ClientService(ClientRepository clientRepository, ValidatorCPF validatorCPF){
        this.clientRepository = clientRepository;
        this.validatorCPF = validatorCPF;
    }

    private Client getClientOrCheckClientExist(String cpf){
        Optional<Client> clientOptional = this.clientRepository.findByCpf(cpf);

        if(clientOptional.isEmpty()){
            throw new EntityNotFoundException("Não existe registro de cliente com esse CPF.");
        }

        return clientOptional.get();
    }

    public Client create(Client client){
        this.validatorCPF.validate(client.getCpf());

        if(clientRepository.existsByCpf(client.getCpf())){
            throw new DataIntegrityViolationException("Já existe um cliente com esse número de CPF.");
        }

        if(this.clientRepository.existsByEmail(client.getEmail())){
            throw new DataIntegrityViolationException("Já existe um cliente com esse endereço de E-mail.");
        }

        return this.clientRepository.save(client);
    }

    public List<Client> get(){
        return this.clientRepository.findAll();
    }

    public Client getByCPF(String cpf){
        this.validatorCPF.validate(cpf);
        return this.getClientOrCheckClientExist(cpf);
    }

    public Client update(String cpf, Client client){
        this.validatorCPF.validate(cpf);

        Client clientToUpdate = this.getClientOrCheckClientExist(cpf);

        clientToUpdate.setName(client.getName());
        clientToUpdate.setEmail(client.getEmail());
        clientToUpdate.setCpf(client.getCpf());

        return this.clientRepository.save(clientToUpdate);
    }

    public void delete(String cpf){
        this.validatorCPF.validate(cpf);
        this.getClientOrCheckClientExist(cpf);

        this.clientRepository.deleteByCpf(cpf);
    }
}
