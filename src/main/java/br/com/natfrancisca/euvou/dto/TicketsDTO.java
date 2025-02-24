package br.com.natfrancisca.euvou.dto;

import br.com.natfrancisca.euvou.model.Tickets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketsDTO {
    private Long id;
    private int amount;
    private LocalDateTime dataAccessTickets;
    private int numberDaysCloseAccessTickets;
    private boolean accessStatus;
    private EventDTO event;

    public static TicketsDTO fromEntity(Tickets tickets){
        return new TicketsDTO(
                tickets.getId(),
                tickets.getAmount(),
                tickets.getDateAccessTickets(),
                tickets.getNumberDaysCloseAccessTickets(),
                tickets.isAccessStatus(),
                EventDTO.fromEntity(tickets.getEvent())
        );
    }
}
