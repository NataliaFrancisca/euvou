package br.com.natfrancisca.euvou.exception;

public class CPFException extends IllegalArgumentException {
    public CPFException(){
        super("Já existe um cliente com este CPF.");
    }
}
