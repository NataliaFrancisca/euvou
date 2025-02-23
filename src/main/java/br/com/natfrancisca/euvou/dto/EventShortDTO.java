package br.com.natfrancisca.euvou.dto;

import br.com.natfrancisca.euvou.model.Address;
import br.com.natfrancisca.euvou.model.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventShortDTO {
    private Long id;
    private String name;
    private Address address;
    private LocalDateTime date;

    public static EventShortDTO fromEntity(Event event){
        EventShortDTO eventShortDTO = new EventShortDTO();

        eventShortDTO.setId(event.getId());
        eventShortDTO.setName(event.getName());
        eventShortDTO.setAddress(event.getAddress());
        eventShortDTO.setDate(event.getDate());

        return eventShortDTO;
    }
}
