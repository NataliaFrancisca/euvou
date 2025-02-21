package br.com.natfrancisca.euvou.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Event")
@Table(name = "Event")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Valid
    @Embedded
    @Column(nullable = false)
    private Address address;

    @Column(nullable = false)
    @Valid
    private LocalDateTime date;

    @Column()
    private Long organizer_id;

    @ManyToOne
    @JoinColumn(name = "organizer_id", insertable = false, updatable = false)
    private Organizer organizer;

    public Event(Event event, Organizer organizer){
        this.id = event.getId();
        this.name = event.getName();
        this.address = event.getAddress();
        this.date = event.getDate();
        this.organizer_id = event.getOrganizer_id();
        this.organizer = organizer;
    }
}
