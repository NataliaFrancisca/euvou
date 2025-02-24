package br.com.natfrancisca.euvou.util;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.natfrancisca.euvou.exception.InvalidCPFException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidatorCPF {
    public void validate(String cpf){
        boolean isCPFValid = isCPFValid(cpf);

        if(!isCPFValid){
            throw new InvalidCPFException("Digite um CPF válido ex.: xxx.xxx.xxx-xx");
        }
    }

    private boolean isCPFValid(String cpf){
        CPFValidator cpfValidator = new CPFValidator();
        List<ValidationMessage> errors = cpfValidator.invalidMessagesFor(cpf);
        return errors.isEmpty();
    }
}
