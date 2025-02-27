package br.com.natfrancisca.euvou.dto;

import br.com.natfrancisca.euvou.model.Client;
import br.com.natfrancisca.euvou.model.Ticket;
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

    @NotNull(message = "Identificação do evento é obrigatório.")
    private Long event_id;

    @NotNull(message = "Identificação do cliente é obrigatório.")
    private Long client_id;

    private EventShortDTO event;
    private Client client;

    public Ticket toEntity(){
        Ticket ticket = new Ticket();

        ticket.setId(this.id);
        ticket.setEvent_id(this.event_id);
        ticket.setClient_id(this.client_id);

        return ticket;
    }

    public static TicketDTO fromEntity(Ticket ticket){
        TicketDTO ticketDTO = new TicketDTO();

        ticketDTO.setId(ticket.getId());
        ticketDTO.setEvent_id(ticket.getEvent_id());
        ticketDTO.setClient_id(ticket.getClient_id());
        ticketDTO.setClient(ticket.getClient());
        ticketDTO.setEvent(EventShortDTO.fromEntity(ticket.getEvent()));

        return ticketDTO;
    }
}
