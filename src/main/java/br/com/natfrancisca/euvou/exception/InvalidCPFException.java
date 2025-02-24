package br.com.natfrancisca.euvou.exception;

public class InvalidCPFException extends IllegalArgumentException {
    public InvalidCPFException(String message){
        super(message);
    }
}
