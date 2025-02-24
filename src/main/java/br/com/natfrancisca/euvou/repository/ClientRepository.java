package br.com.natfrancisca.euvou.repository;
import br.com.natfrancisca.euvou.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    Optional<Client> findByCpf(String cpf);
    void deleteByCpf(String cpf);
}
