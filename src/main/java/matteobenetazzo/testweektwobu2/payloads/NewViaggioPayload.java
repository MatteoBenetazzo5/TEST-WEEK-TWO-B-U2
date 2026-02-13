package matteobenetazzo.testweektwobu2.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class NewViaggioPayload {

    @NotBlank(message = "La destinazione è obbligatoria")
    private String destinazione;

    @NotNull(message = "La data è obbligatoria")
    private LocalDate data;
}

