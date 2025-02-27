package br.com.natfrancisca.euvou.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity(name = "Tickets")
@Table(name = "Tickets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Min(2)
    private int amount;

    @Column(nullable = false)
    @FutureOrPresent
    private LocalDateTime dateAccessTickets;

    @Column(nullable = false)
    @Min(1)
    private int numberDaysCloseAccessTickets;

    @Column(nullable = false)
    private Long event_id;

    @ManyToOne
    @JoinColumn(name = "event_id", insertable = false, updatable = false)
    private Event event;

    @Column(nullable = false)
    private Boolean accessStatus = true;

    public Tickets(Tickets tickets, Event event){
        this.id = tickets.id;
        this.amount = tickets.getAmount();
        this.dateAccessTickets = tickets.getDateAccessTickets();
        this.numberDaysCloseAccessTickets = tickets.getNumberDaysCloseAccessTickets();
        this.event_id = tickets.getEvent_id();
        this.event = event;
    }
}
