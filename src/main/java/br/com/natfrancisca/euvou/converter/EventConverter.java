package br.com.natfrancisca.euvou.converter;

import br.com.natfrancisca.euvou.dto.EventDTO;
import br.com.natfrancisca.euvou.dto.OrganizerShortDTO;
import br.com.natfrancisca.euvou.model.Event;

public class EventConverter {
  public static EventDTO convertToSummary(Event event){

      EventDTO eventDTO = new EventDTO();
      eventDTO.setName(event.getName());
      eventDTO.setAddress(event.getAddress());
      eventDTO.setDate(event.getDate());

      OrganizerShortDTO organizerShortDTO = new OrganizerShortDTO();
      organizerShortDTO.setName(event.getOrganizer().getName());
      organizerShortDTO.setEmail(event.getOrganizer().getEmail());
      organizerShortDTO.setCnpj(event.getOrganizer().getCnpj());

      eventDTO.setOrganizer(organizerShortDTO);

      return eventDTO;
  }
}
