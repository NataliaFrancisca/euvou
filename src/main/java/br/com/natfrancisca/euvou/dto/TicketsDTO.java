package br.com.natfrancisca.euvou.dto;

import br.com.natfrancisca.euvou.model.Tickets;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketsDTO {
    private Long id;

    @NotNull(message = "Quantidade de ingressos é obrigatório.")
    private int amount;

    @NotNull(message = "Data de acesso aos ingressos é obrigatório.")
    @FutureOrPresent(message = "Data de acesso deve ser no presente/futuro.")
    private LocalDateTime dateAccessTickets;

    @NotNull(message = "Número de dias para acesso aos ingressos é obrigatório.")
    private int numberDaysCloseAccessTickets;
    private boolean accessStatus;

    @NotNull
    private Long event_id;

    private EventDTO event;

    public Tickets toEntity(){
        Tickets tickets = new Tickets();

        tickets.setId(this.id);
        tickets.setAmount(this.amount);
        tickets.setDateAccessTickets(this.dateAccessTickets);
        tickets.setNumberDaysCloseAccessTickets(this.numberDaysCloseAccessTickets);

        return tickets;
    }

    public static TicketsDTO fromEntity(Tickets tickets){
        TicketsDTO ticketsDTO = new TicketsDTO();

        ticketsDTO.setId(tickets.getId());
        ticketsDTO.setAmount(tickets.getAmount());
        ticketsDTO.setDateAccessTickets(tickets.getDateAccessTickets());
        ticketsDTO.setNumberDaysCloseAccessTickets(tickets.getNumberDaysCloseAccessTickets());
        ticketsDTO.setEvent(EventDTO.fromEntity(tickets.getEvent()));

        return ticketsDTO;
    }
}
