package matteobenetazzo.testweektwobu2.repositories;

import matteobenetazzo.testweektwobu2.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DipendentiRepository extends JpaRepository<Dipendente, UUID> {
    /* Potrebbe non esistere un dipendente con questa email,
    quindi ho deciso di far ritornare un Optional invece di un oggetto diretto. */

    Optional<Dipendente> findByEmail(String email);

    Optional<Dipendente> findByUsername(String username);
}

