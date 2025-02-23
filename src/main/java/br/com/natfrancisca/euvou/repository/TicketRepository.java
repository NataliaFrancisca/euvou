package br.com.natfrancisca.euvou.repository;

import br.com.natfrancisca.euvou.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsByClientId(Long idClient);
    boolean existsByEventId(Long id);
}
