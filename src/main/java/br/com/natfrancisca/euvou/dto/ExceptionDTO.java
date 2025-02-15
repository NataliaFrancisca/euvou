package br.com.natfrancisca.euvou.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionDTO {
    private int httpStatus;
    private String status;
    private String message;
    private Map<String, String> errors;

    public static ResponseEntity<ExceptionDTO> create(HttpStatus httpStatus, String status, String message, Map<String, String> errors){
        ExceptionDTO response = new ExceptionDTO(httpStatus.value(), status, message, errors);
        return ResponseEntity.status(httpStatus).body(response);
    }
}
