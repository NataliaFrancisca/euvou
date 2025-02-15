package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.dto.ResponseDTO;
import br.com.natfrancisca.euvou.exception.CPFException;
import br.com.natfrancisca.euvou.exception.CPFInvalidException;
import br.com.natfrancisca.euvou.exception.EmailException;
import br.com.natfrancisca.euvou.model.Client;
import br.com.natfrancisca.euvou.repository.ClientRepository;
import br.com.natfrancisca.euvou.util.CPFValidation;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    final private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public Client create(Client client){
        if(clientRepository.existsByCpf(client.getCpf())){
            throw new CPFException();
        }

        if(clientRepository.existsByEmail(client.getEmail())){
            throw new EmailException();
        }

        return this.clientRepository.save(client);
    }

    public List<Client> get(){
        return this.clientRepository.findAll();
    }

    public Client getClient(String cpf){
        boolean cpfValidation = new CPFValidation().isValid(cpf);

        if(!cpfValidation){
            throw new CPFInvalidException();
        }

        if(!clientRepository.existsByCpf(cpf)){
            throw new IllegalArgumentException("Não existe registro de cliente com ess CPF.");
        }

        return clientRepository.getByCpf(cpf);
    }
}
