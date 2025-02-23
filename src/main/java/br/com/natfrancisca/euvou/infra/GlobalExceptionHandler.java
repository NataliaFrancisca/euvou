package br.com.natfrancisca.euvou.infra;

import br.com.natfrancisca.euvou.dto.APIResponseDTO;
import br.com.natfrancisca.euvou.exception.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<APIResponseDTO> handleJsonParseError(HttpMessageNotReadableException ex){
        System.out.println(ex);
        return APIResponseDTO.create(HttpStatus.BAD_REQUEST, "O JSON enviado não está correto, verifique os campos antes de enviar uma nova requisição.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponseDTO> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return APIResponseDTO.create(HttpStatus.BAD_REQUEST, "Ocorreu um erro na requisição", errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponseDTO> handleConstraintViolationException(ConstraintViolationException ex){
        Map<String, String> errorMessages = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
        });

        return APIResponseDTO.create(HttpStatus.BAD_REQUEST, "Ocorreu um erro na requisição", errorMessages);
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

    @ExceptionHandler(OrganizerException.class)
    public ResponseEntity<APIResponseDTO> handleOrganizerException(OrganizerException ex){
        return APIResponseDTO.create(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(TicketException.class)
    public ResponseEntity<APIResponseDTO> handleTicketException(TicketException ex){
        return APIResponseDTO.create(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
