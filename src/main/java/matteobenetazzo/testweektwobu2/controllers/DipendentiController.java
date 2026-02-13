package matteobenetazzo.testweektwobu2.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweektwobu2.entities.Dipendente;
import matteobenetazzo.testweektwobu2.payloads.NewDipendentePayload;
import matteobenetazzo.testweektwobu2.services.DipendentiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
@RequiredArgsConstructor
public class DipendentiController {

    private final DipendentiService dipendentiService;
    /* In questo controller scrivo gli endpoint che userò poi su Postman.
     * Ogni metodo corrisponde a un'operazione HTTP:
     * GET per leggere,
     * POST per creare,
     * PUT per modificare,
     * DELETE per eliminare.
     * Tramite questi endpoint posso salvare e recuperare dati dal database.
     */

    // GET
    @GetMapping
    public List<Dipendente> getAll() {
        return dipendentiService.findAll();
    }

    // GET
    @GetMapping("/{id}")
    public Dipendente getById(@PathVariable UUID id) {
        return dipendentiService.findById(id);
    }

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente create(@RequestBody @Valid NewDipendentePayload payload) {
        Dipendente dipendente = new Dipendente(
                payload.getUsername(),
                payload.getNome(),
                payload.getCognome(),
                payload.getEmail()
        );
        return dipendentiService.save(dipendente);
    }

    // PUT
    @PutMapping("/{id}/assegna")
    public Dipendente assegnaViaggio(
            @PathVariable UUID id,
            @RequestParam UUID viaggioId
    ) {
        return dipendentiService.assegnaViaggio(id, viaggioId);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        dipendentiService.delete(id);
    }

    /*
     * Nota:
     * L'upload dell'immagine profilo del dipendente non l'ho implementato
     * per motivi di tempo. La funzionalità potrebbe essere aggiunta tramite
     * un endpoint PUT con MultipartFile e integrazione Cloudinary,
     * salvando poi l'URL restituito nel campo immagineProfilo del dipendente.
     */

}


