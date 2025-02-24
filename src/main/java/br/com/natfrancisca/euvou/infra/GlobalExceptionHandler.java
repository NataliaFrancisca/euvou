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
    // PARA QUANDO A REQUISIÇÃO MANDA UM BODY INCORRETO
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<APIResponseDTO> handleJsonParseError(HttpMessageNotReadableException ex){
        return APIResponseDTO.create(HttpStatus.BAD_REQUEST, "O JSON enviado não está correto, verifique os campos antes de enviar uma nova requisição.");
    }

    // LIDANDO COM ERROS DA VALIDAÇÃO DOS CAMPOS
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<APIResponseDTO> handleValidationException(Exception ex){
        Map<String, String> errors = new HashMap<>();

        if(ex instanceof MethodArgumentNotValidException){
            ((MethodArgumentNotValidException) ex)
                    .getBindingResult()
                    .getFieldErrors().forEach(error -> {
                        errors.put(error.getField(), error.getDefaultMessage());
                    });
        } else if(ex instanceof ConstraintViolationException){
            ((ConstraintViolationException) ex)
                    .getConstraintViolations()
                    .forEach(violation -> {
                        errors.put(violation.getPropertyPath().toString(), violation.getMessage());
                    });
        }

        return APIResponseDTO.create(HttpStatus.BAD_REQUEST, "Ocorreu um erro na requisição.", errors);
    }

    @ExceptionHandler({
            ClientException.class,
            InvalidCPFException.class,
            OrganizerException.class,
            TicketException.class,
            TicketsException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<APIResponseDTO> handleBadRequestExceptions(Exception ex){
        return APIResponseDTO.create(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler({
            DataIntegrityViolationException.class,
            EmailException.class
    })
    public ResponseEntity<APIResponseDTO> handleConflictExceptions(Exception ex){
        return APIResponseDTO.create(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<APIResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex){
        return APIResponseDTO.create(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
