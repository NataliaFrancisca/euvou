package br.com.natfrancisca.euvou.model;

import jakarta.persistence.*;
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

    @Embedded
    private Address address;

    @Column(nullable = false)
    private LocalDateTime date;

    private Long organizer_id;

    @ManyToOne
    @JoinColumn(name = "organizer_id", insertable = false, updatable = false)
    private Organizer organizer;

    public Event(Event event, Organizer organizer){
        this.name = event.getName();
        this.address = event.getAddress();
        this.date = event.getDate();
        this.organizer_id = event.getOrganizer_id();
        this.organizer = organizer;
    }
}
