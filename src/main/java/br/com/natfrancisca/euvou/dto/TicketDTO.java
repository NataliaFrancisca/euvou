package br.com.natfrancisca.euvou.dto;

import br.com.natfrancisca.euvou.model.Client;
import br.com.natfrancisca.euvou.model.Event;
import br.com.natfrancisca.euvou.model.Ticket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Long id;
    private EventShortDTO event;
    private Client client;

    public static TicketDTO fromEntity(Ticket ticket){
        return new TicketDTO(
                ticket.getId(),
                EventShortDTO.fromEntity(ticket.getEvent()),
                ticket.getClient()
        );
    }

}
