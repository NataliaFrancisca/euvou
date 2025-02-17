package br.com.natfrancisca.euvou.infra;

import br.com.natfrancisca.euvou.dto.ExceptionDTO;
import br.com.natfrancisca.euvou.dto.ResponseDTO;
import br.com.natfrancisca.euvou.exception.CPFException;
import br.com.natfrancisca.euvou.exception.CPFInvalidException;
import br.com.natfrancisca.euvou.exception.ClientException;
import br.com.natfrancisca.euvou.exception.EmailException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ExceptionDTO.create(HttpStatus.BAD_REQUEST, "Falha", "Ocorreu um erro na requisição", errors);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ResponseDTO> handleClientException(ClientException ex){
        return ResponseDTO.create(HttpStatus.NOT_FOUND,  ex.getMessage());
    }

    @ExceptionHandler(CPFException.class)
    public ResponseEntity<ResponseDTO> handleCPFException(CPFException ex){
        return ResponseDTO.create(HttpStatus.BAD_REQUEST,  ex.getMessage());
    }

    @ExceptionHandler(CPFInvalidException.class)
    public ResponseEntity<ResponseDTO> handleCPFException(CPFInvalidException ex){
        return ResponseDTO.create(HttpStatus.BAD_REQUEST,  ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex){
        return ResponseDTO.create(HttpStatus.BAD_REQUEST,  ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDTO> handleDatInegrityViolationException(DataIntegrityViolationException ex){
        return ResponseDTO.create(HttpStatus.CONFLICT,  "Já existe um cadastro com esse número de CPF.");
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<ResponseDTO> handleEmailException(EmailException ex){
        return ResponseDTO.create(HttpStatus.CONFLICT,  "Já existe um cadastro com esse endereço de email.");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseDTO.create(HttpStatus.NOT_FOUND, "Não foi retornar o dado requisitado.");
    }
}
