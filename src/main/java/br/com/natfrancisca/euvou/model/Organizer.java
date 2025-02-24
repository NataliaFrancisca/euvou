package br.com.natfrancisca.euvou.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity(name = "organizer")
@Table(name = "organizer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organizer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    @CNPJ
    private String cnpj;
}
