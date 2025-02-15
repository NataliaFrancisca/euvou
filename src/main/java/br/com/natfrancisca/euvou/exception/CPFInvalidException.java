package br.com.natfrancisca.euvou.exception;

public class CPFInvalidException extends RuntimeException {
    public CPFInvalidException() {
        super("Digite um CPF válido ex.: xxx.xxx.xxx-xx");
    }
}
