package br.com.natfrancisca.euvou.dto;

import br.com.natfrancisca.euvou.model.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
public class ClientDTO {
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O CPF é obrigatório.")
    @CPF(message = "Necessário um CPF válido para realizar cadastro.")
    private String cpf;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Necessário um E-mail válido para realizar cadastro.")
    private String email;

    public Client toEntity(){
        Client client = new Client();

        client.setName(this.name);
        client.setCpf(this.cpf);
        client.setEmail(this.email);

        return client;
    }
}
