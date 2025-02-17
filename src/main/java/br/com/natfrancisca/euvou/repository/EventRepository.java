package br.com.natfrancisca.euvou.repository;

import br.com.natfrancisca.euvou.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
