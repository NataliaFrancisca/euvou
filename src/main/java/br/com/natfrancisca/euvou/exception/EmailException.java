package br.com.natfrancisca.euvou.exception;

public class EmailException extends RuntimeException {
    public EmailException() {
        super("Já existe um cliente com este email.");
    }
}
