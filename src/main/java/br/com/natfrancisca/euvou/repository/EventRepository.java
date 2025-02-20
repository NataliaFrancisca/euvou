package br.com.natfrancisca.euvou.repository;

import br.com.natfrancisca.euvou.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByName(String name);
    List<Event> findByNameContainingIgnoreCase(String name);
}
