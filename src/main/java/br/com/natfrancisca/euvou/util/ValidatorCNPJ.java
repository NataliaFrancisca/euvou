package br.com.natfrancisca.euvou.util;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.natfrancisca.euvou.exception.InvalidCPFException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidatorCNPJ {
    public void validate(String cnpj){
        boolean isCNPJ = isCNPJValid(cnpj);

        if(!isCNPJ){
            throw new InvalidCPFException("Digite um CNPJ válido e somente os números");
        }
    }

    private boolean isCNPJValid(String cnpj){
        CNPJValidator cnpjValidator = new CNPJValidator();
        List<ValidationMessage> errors = cnpjValidator.invalidMessagesFor(cnpj);
        return errors.isEmpty();
    }
}
