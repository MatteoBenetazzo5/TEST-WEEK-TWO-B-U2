package matteobenetazzo.testweektwobu2.services;

import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweektwobu2.entities.StatoViaggio;
import matteobenetazzo.testweektwobu2.entities.Viaggio;
import matteobenetazzo.testweektwobu2.repositories.ViaggiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ViaggiService {

    private final ViaggiRepository viaggiRepository;

    // Ritorno tutti i viaggi presenti nel DB
    public List<Viaggio> findAll() {
        return viaggiRepository.findAll();
    }

    // Cerco un viaggio tramite id. Se non esiste, lancio un'eccezione
    public Viaggio findById(UUID id) {
        return viaggiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaggio non trovato"));
    }

    // Salvo un nuovo viaggio nel DB
    public Viaggio save(Viaggio viaggio) {
        return viaggiRepository.save(viaggio);
    }

    // Aggiorno lo stato di un viaggio (IN_PROGRAMMA o COMPLETATO)
    public Viaggio updateStato(UUID id, StatoViaggio nuovoStato) {
        Viaggio viaggio = findById(id);
        viaggio.setStato(nuovoStato);
        return viaggiRepository.save(viaggio);
    }

    // Elimino un viaggio dal DB
    public void delete(UUID id) {
        Viaggio viaggio = findById(id);
        viaggiRepository.delete(viaggio);
    }
}

