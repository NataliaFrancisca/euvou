package br.com.natfrancisca.euvou.dto;

import br.com.natfrancisca.euvou.model.Client;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    private int httpStatus;
    private String message;
    private List<?> body;
    private Client client;

    public ResponseDTO(HttpStatus httpStatus, String message, List<?> body, Client client){
        this.httpStatus = httpStatus.value();
        this.message = message;
        this.body = body;
        this.client = client;
    }

    public static ResponseEntity<ResponseDTO> create(HttpStatus httpStatus, String message){
        return ResponseEntity.status(httpStatus).body(new ResponseDTO(httpStatus, message, null, null));
    }

    public static ResponseEntity<ResponseDTO> create(HttpStatus httpStatus, List<?> body){
        return ResponseEntity.status(httpStatus).body(new ResponseDTO(httpStatus, null, body, null));
    }

    public static ResponseEntity<ResponseDTO> create(HttpStatus httpStatus, Client client){
        return ResponseEntity.status(httpStatus).body(new ResponseDTO(httpStatus, null, null, client));
    }
}
