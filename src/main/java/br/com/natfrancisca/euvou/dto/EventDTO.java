package br.com.natfrancisca.euvou.dto;


import br.com.natfrancisca.euvou.model.Address;
import br.com.natfrancisca.euvou.model.Event;
import br.com.natfrancisca.euvou.model.Organizer;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDTO {
    private Long id;
    @NotBlank(message = "Nome do evento é obrigatório.")
    private String name;
    @NotNull(message = "Endereço do evento é obrigatório.")
    private Address address;
    @NotNull(message = "Data do evento é obrigatório.")
    @Future(message = "Data do evento deve ser no futuro.")
    private LocalDateTime date;
    @NotNull(message = "Necessário indicar o responsável pelo evento.")
    private Long organizer_id;

    private Organizer organizer;

    public Event toEntity(){
        Event event = new Event();

        event.setId(this.id);
        event.setName(this.name);
        event.setDate(this.date);
        event.setAddress(this.address);
        event.setOrganizer_id(this.organizer_id);

        return event;
    }

    public static EventDTO fromEntity(Event event){
        EventDTO eventDTO = new EventDTO();

        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDate(event.getDate());
        eventDTO.setAddress(event.getAddress());
        eventDTO.setOrganizer(event.getOrganizer());

        return eventDTO;
    }
}
