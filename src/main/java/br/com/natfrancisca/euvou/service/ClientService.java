package br.com.natfrancisca.euvou.service;

import br.com.natfrancisca.euvou.exception.CPFException;
import br.com.natfrancisca.euvou.exception.CPFInvalidException;
import br.com.natfrancisca.euvou.exception.ClientException;
import br.com.natfrancisca.euvou.exception.EmailException;
import br.com.natfrancisca.euvou.model.Client;
import br.com.natfrancisca.euvou.repository.ClientRepository;
import br.com.natfrancisca.euvou.util.CPFValidation;
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

    private void validationCPF(String cpf){
        boolean cpfValidation = new CPFValidation().isValid(cpf);

        if(!cpfValidation){
            throw new CPFInvalidException();
        }
    }

    private void isCPFAlreadyUsed(String cpf){
        if(clientRepository.existsByCpf(cpf)){
            throw new CPFException();
        }
    }

    public Client create(Client client){
        isCPFAlreadyUsed(client.getCpf());

        if(clientRepository.existsByEmail(client.getEmail())){
            throw new EmailException();
        }

        return this.clientRepository.save(client);
    }

    public List<Client> get(){
        return this.clientRepository.findAll();
    }

    public Client getClient(String cpf){
        validationCPF(cpf);
        isCPFAlreadyUsed(cpf);
        return this.clientRepository.findByCpf(cpf);
    }

    public Client update(String cpf, Client client){
        validationCPF(cpf);

        if(!this.clientRepository.existsByCpf(cpf)){
            throw new ClientException("Não existe cliente com esse CPF.");
        }

        Client clientToUpdate = this.clientRepository.findByCpf(cpf);

        clientToUpdate.setEmail(client.getEmail());
        clientToUpdate.setName(client.getName());
        clientToUpdate.setCpf(client.getCpf());

        return this.clientRepository.save(clientToUpdate);
    }

    public void delete(String cpf){
        validationCPF(cpf);

        if(!this.clientRepository.existsByCpf(cpf)){
            throw new ClientException("Não existe cliente com esse CPF.");
        }

        this.clientRepository.deleteByCpf(cpf);
    }
}
