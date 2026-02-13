package matteobenetazzo.testweektwobu2.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweektwobu2.entities.Prenotazione;
import matteobenetazzo.testweektwobu2.payloads.NewPrenotazionePayload;
import matteobenetazzo.testweektwobu2.services.PrenotazioniService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioniController {

    private final PrenotazioniService prenotazioniService;
    /* In questo controller scrivo gli endpoint che userò poi su Postman.
     * Ogni metodo corrisponde a un'operazione HTTP:
     * GET per leggere,
     * POST per creare,
     * DELETE per eliminare.
     * Tramite questi endpoint posso salvare e recuperare dati dal database.
     */

    // GET
    @GetMapping
    public List<Prenotazione> getAll() {
        return prenotazioniService.findAll();
    }

    // GET
    @GetMapping("/{id}")
    public Prenotazione getById(@PathVariable UUID id) {
        return prenotazioniService.findById(id);
    }

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione create(@RequestBody @Valid NewPrenotazionePayload payload) {
        return prenotazioniService.save(
                payload.getDipendenteId(),
                payload.getViaggioId(),
                payload.getDataRichiesta(),
                payload.getPreferenze()
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        prenotazioniService.delete(id);
    }
}


