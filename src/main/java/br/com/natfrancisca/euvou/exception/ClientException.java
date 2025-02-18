package br.com.natfrancisca.euvou.exception;

public class ClientException extends IllegalArgumentException {
    public ClientException(String message){
        super(message);
    }
}
