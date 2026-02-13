package matteobenetazzo.testweektwobu2.services;

import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweektwobu2.entities.Dipendente;
import matteobenetazzo.testweektwobu2.entities.Prenotazione;
import matteobenetazzo.testweektwobu2.entities.Viaggio;
import matteobenetazzo.testweektwobu2.exceptions.BadRequestException;
import matteobenetazzo.testweektwobu2.exceptions.NotFoundException;
import matteobenetazzo.testweektwobu2.repositories.DipendentiRepository;
import matteobenetazzo.testweektwobu2.repositories.PrenotazioniRepository;
import matteobenetazzo.testweektwobu2.repositories.ViaggiRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrenotazioniService {
    private final PrenotazioniRepository prenotazioniRepository;
    private final DipendentiRepository dipendentiRepository;
    private final ViaggiRepository viaggiRepository;

    // Metodo per vedere tutte le prenotazioni
    public List<Prenotazione> findAll() {
        return prenotazioniRepository.findAll();
    }

    // Metodo che cerca una prenotazione tramite id. Se non esiste, lancio un errore
    public Prenotazione findById(UUID id) {
        return prenotazioniRepository.findById(id).orElseThrow(() -> new NotFoundException("Prenotazione non trovata"));
    }

    // Metodo per creare una prenotazione
    public Prenotazione save(UUID dipendenteId, UUID viaggioId, LocalDate dataRichiesta, String preferenze) {

        // 1) stesso dipendente + stessa data = non si può fare
        boolean dipendenteGiaPrenotatoInQuellaData = prenotazioniRepository.existsByDipendenteIdAndDataRichiesta(dipendenteId, dataRichiesta);

        // 2) se la regola non viene rispettata, lancio un errore
        if (dipendenteGiaPrenotatoInQuellaData) {
            throw new BadRequestException("Questo dipendente ha già una prenotazione per questa data");
        }

        // 3) recupero il dipendente dal DB: se l'id è sbagliato e non esiste, lancio un errore
        Dipendente dipendente = dipendentiRepository.findById(dipendenteId).orElseThrow(() -> new NotFoundException("Dipendente non trovato"));

        // 4) recupero anche il viaggio dal DB: se il viaggio non esiste non posso creare una prenotazione valida
        Viaggio viaggio = viaggiRepository.findById(viaggioId).orElseThrow(() -> new NotFoundException("Viaggio non trovato"));

        // 5) creo l'oggetto Prenotazione, passandogli: dipendente, viaggio, data e preferenze
        Prenotazione nuovaPrenotazione = new Prenotazione(dipendente, viaggio, dataRichiesta, preferenze);

        // 6) salvo nel database i miei dati e ritorno l'oggetto salvato con il su ID univoco
        return prenotazioniRepository.save(nuovaPrenotazione);
    }

    // Metodo che elimina una prenotazione: prima la cerco e poi la cancello
    public void delete(UUID id) {
        Prenotazione prenotazione = findById(id);
        prenotazioniRepository.delete(prenotazione);
    }
}

