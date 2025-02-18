package br.com.natfrancisca.euvou.dto;

import br.com.natfrancisca.euvou.model.Client;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponseDTO {
    private int httpStatus;
    private String message;
    private List<?> body;
    private Client client;
    private Map<String, String> errors;

    public APIResponseDTO(HttpStatus httpStatus, String message, List<?> body, Client client, Map<String, String> errors){
        this.httpStatus = httpStatus.value();
        this.message = message;
        this.body = body;
        this.client = client;
        this.errors = errors;
    }

    public static ResponseEntity<APIResponseDTO> create(HttpStatus httpStatus, String message){
        return ResponseEntity.status(httpStatus).body(new APIResponseDTO(httpStatus, message, null, null, null));
    }

    public static ResponseEntity<APIResponseDTO> create(HttpStatus httpStatus, List<?> body){
        return ResponseEntity.status(httpStatus).body(new APIResponseDTO(httpStatus, null, body, null, null));
    }

    public static ResponseEntity<APIResponseDTO> create(HttpStatus httpStatus, Client client){
        return ResponseEntity.status(httpStatus).body(new APIResponseDTO(httpStatus, null, null, client, null));
    }

    public static ResponseEntity<APIResponseDTO> create(HttpStatus httpStatus, String message, Map<String, String> errors){
        return ResponseEntity.status(httpStatus).body(new APIResponseDTO(httpStatus, message, null, null, errors));
    }
}
