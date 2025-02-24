package br.com.natfrancisca.euvou.repository;

import br.com.natfrancisca.euvou.model.Organizer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {
    Optional<Organizer> findOrganizerByCnpj(String cnpj);
    boolean existsByCnpj(String cnpj);
    boolean existsByEmail(String email);
    void deleteByCnpj(String cnpj);
}