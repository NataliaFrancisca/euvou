package br.com.natfrancisca.euvou.dto;

import br.com.natfrancisca.euvou.model.Address;
import br.com.natfrancisca.euvou.model.Event;
import br.com.natfrancisca.euvou.model.Organizer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDTO {
    private String name;
    private Address address;
    private LocalDateTime date;
    private Long organizer_id;
    private OrganizerShortDTO organizer;

    public Event toEntity(){
        Event event = new Event();

        event.setName(this.name);
        event.setDate(this.date);
        event.setAddress(this.address);
        event.setOrganizer_id(this.organizer_id);

        return event;
    }
}
