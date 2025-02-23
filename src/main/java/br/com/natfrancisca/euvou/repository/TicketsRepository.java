package br.com.natfrancisca.euvou.repository;

import br.com.natfrancisca.euvou.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketsRepository extends JpaRepository<Tickets, Long> {
    boolean existsByEventId(Long id);
    Tickets getByEventId(Long id);

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.event.id = :eventId")
    Long countTicketsByEvent(@Param("eventId") Long eventId);
}
