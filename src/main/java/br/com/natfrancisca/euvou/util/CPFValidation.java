package br.com.natfrancisca.euvou.util;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;


import java.util.List;

public class CPFValidation {
    public boolean isValid(String cpf){
        if(cpf == null){
            return false;
        }

        return CPFIsValid(cpf);
    }

    private boolean CPFIsValid(String cpf){
        CPFValidator cpfValidator = new CPFValidator();
        List<ValidationMessage> errors = cpfValidator.invalidMessagesFor(cpf);
        return errors.isEmpty();
    }
}
