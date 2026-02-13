package matteobenetazzo.testweektwobu2.services;

import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweektwobu2.entities.Dipendente;
import matteobenetazzo.testweektwobu2.entities.Viaggio;
import matteobenetazzo.testweektwobu2.repositories.DipendentiRepository;
import matteobenetazzo.testweektwobu2.repositories.ViaggiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DipendentiService {

    private final DipendentiRepository dipendentiRepository;
    private final ViaggiRepository viaggiRepository;

    // Ritorno tutti i dipendenti
    public List<Dipendente> findAll() {
        return dipendentiRepository.findAll();
    }

    // Cerco un dipendente per id
    public Dipendente findById(UUID id) {
        return dipendentiRepository.findById(id).orElseThrow(() -> new RuntimeException("Dipendente non trovato"));
    }

    // Salvo un nuovo dipendente
    public Dipendente save(Dipendente dipendente) {
        return dipendentiRepository.save(dipendente);
    }

    // Assegno un dipendente a un viaggio
    public Dipendente assegnaViaggio(UUID dipendenteId, UUID viaggioId) {

        // 1) Cerco il dipendente
        Dipendente dipendente = findById(dipendenteId);

        // 2) Cerco il viaggio
        Viaggio viaggio = viaggiRepository.findById(viaggioId).orElseThrow(() -> new RuntimeException("Viaggio non trovato"));

        // 3) Collego il viaggio al dipendente
        dipendente.setViaggioAssegnato(viaggio);

        // 4) Salvo la modifica
        return dipendentiRepository.save(dipendente);
    }

    // Elimino un dipendente
    public void delete(UUID id) {
        Dipendente dipendente = findById(id);
        dipendentiRepository.delete(dipendente);
    }
}

