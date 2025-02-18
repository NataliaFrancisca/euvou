package br.com.natfrancisca.euvou.dto;

import br.com.natfrancisca.euvou.model.Organizer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "cnpj")
public class OrganizerDTO {
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O CPNJ é obrigatório.")
    private String cnpj;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Necessário um E-mail válido.")
    private String email;

   public Organizer toEntity(){
       Organizer organizer = new Organizer();

       organizer.setId(this.id);
       organizer.setName(this.name);
       organizer.setCnpj(this.cnpj);
       organizer.setEmail(this.email);

       return organizer;
   }
}
