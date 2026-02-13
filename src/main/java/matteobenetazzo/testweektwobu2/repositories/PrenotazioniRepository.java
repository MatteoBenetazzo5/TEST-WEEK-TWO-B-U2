package matteobenetazzo.testweektwobu2.repositories;

import matteobenetazzo.testweektwobu2.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {
    boolean existsByDipendenteIdAndDataRichiesta(UUID dipendenteId, LocalDate dataRichiesta);
}
