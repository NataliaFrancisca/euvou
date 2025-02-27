package br.com.natfrancisca.euvou.repository;

import br.com.natfrancisca.euvou.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsByEventId(Long id);
    boolean existsByClientId(Long id);
    Optional<Ticket> findByClientCpf(String cpf);
}
