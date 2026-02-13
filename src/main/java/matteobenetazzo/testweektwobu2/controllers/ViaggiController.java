package matteobenetazzo.testweektwobu2.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import matteobenetazzo.testweektwobu2.entities.StatoViaggio;
import matteobenetazzo.testweektwobu2.entities.Viaggio;
import matteobenetazzo.testweektwobu2.payloads.NewViaggioPayload;
import matteobenetazzo.testweektwobu2.services.ViaggiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
@RequiredArgsConstructor
public class ViaggiController {

    private final ViaggiService viaggiService;
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
    public List<Viaggio> getAll() {
        return viaggiService.findAll();
    }

    // GET
    @GetMapping("/{id}")
    public Viaggio getById(@PathVariable UUID id) {
        return viaggiService.findById(id);
    }

    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio create(@RequestBody @Valid NewViaggioPayload payload) {
        Viaggio viaggio = new Viaggio(
                payload.getDestinazione(),
                payload.getData(),
                StatoViaggio.IN_PROGRAMMA
        );
        return viaggiService.save(viaggio);
    }

    // PUT
    @PutMapping("/{id}/stato")
    public Viaggio updateStato(@PathVariable UUID id, @RequestParam StatoViaggio stato) {
        return viaggiService.updateStato(id, stato);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        viaggiService.delete(id);
    }
}


