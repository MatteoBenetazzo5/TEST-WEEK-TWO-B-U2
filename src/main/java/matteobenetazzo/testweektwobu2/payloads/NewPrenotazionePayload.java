package matteobenetazzo.testweektwobu2.payloads;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class NewPrenotazionePayload {

    @NotNull(message = "Il dipendenteId è obbligatorio")
    private UUID dipendenteId;

    @NotNull(message = "Il viaggioId è obbligatorio")
    private UUID viaggioId;

    @NotNull(message = "La dataRichiesta è obbligatoria")
    private LocalDate dataRichiesta;

    private String preferenze;
}

