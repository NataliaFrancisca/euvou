package br.com.natfrancisca.euvou.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @NotBlank(message = "Necessário indicar o local do evento.")
    @Column(nullable = false)
    private String local;
    @NotBlank(message = "Necessário indicar o cep do evento.")
    @Column(nullable = false)
    private String cep;
    @NotBlank(message = "Necessário indicar a rua do evento.")
    @Column(nullable = false)
    private String street;
    @NotBlank(message = "Necessário indicar o número do endereço do evento.")
    @Column(nullable = false)
    private String number;
    @NotBlank(message = "Necessário indicar o bairro do evento.")
    @Column(nullable = false)
    private String neighborhood;
    @NotBlank(message = "Necessário indicar a cidade do evento.")
    @Column(nullable = false)
    private String city;
    @NotBlank(message = "Necessário indicar o estado do evento.")
    @Column(nullable = false)
    private String state;
}
