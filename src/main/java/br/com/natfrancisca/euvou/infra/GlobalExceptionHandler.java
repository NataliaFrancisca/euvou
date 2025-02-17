package br.com.natfrancisca.euvou.infra;

import br.com.natfrancisca.euvou.dto.APIResponseDTO;
import br.com.natfrancisca.euvou.exception.CPFException;
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
    public ResponseEntity<APIResponseDTO> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return APIResponseDTO.create(HttpStatus.BAD_REQUEST, "Ocorreu um erro na requisição", errors);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<APIResponseDTO> handleClientException(ClientException ex){
        return APIResponseDTO.create(HttpStatus.NOT_FOUND,  ex.getMessage());
    }

    @ExceptionHandler(CPFException.class)
    public ResponseEntity<APIResponseDTO> handleCPFException(CPFException ex){
        return APIResponseDTO.create(HttpStatus.BAD_REQUEST,  ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex){
        return APIResponseDTO.create(HttpStatus.BAD_REQUEST,  ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<APIResponseDTO> handleDatInegrityViolationException(DataIntegrityViolationException ex){
        return APIResponseDTO.create(HttpStatus.CONFLICT,  ex.getMessage());
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<APIResponseDTO> handleEmailException(EmailException ex){
        return APIResponseDTO.create(HttpStatus.CONFLICT,  ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<APIResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex){
        return APIResponseDTO.create(HttpStatus.NOT_FOUND, "Não foi retornar o dado requisitado.");
    }
}
