package matteobenetazzo.testweektwobu2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prenotazioni", uniqueConstraints = @UniqueConstraint(columnNames = {"dipendente_id", "data_richiesta"}))
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prenotazione {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    @ManyToOne
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggio;

    @Column(name = "data_richiesta", nullable = false)
    private LocalDate dataRichiesta;

    /* Preferenze (es. volo, alloggio, ecc.).*/
    @Column(columnDefinition = "TEXT")
    private String preferenzePrenotazioni;

    public Prenotazione(Dipendente dipendente,
                        Viaggio viaggio,
                        LocalDate dataRichiesta,
                        String preferenzePrenotazioni) {
        this.dipendente = dipendente;
        this.viaggio = viaggio;
        this.dataRichiesta = dataRichiesta;
        this.preferenzePrenotazioni = preferenzePrenotazioni;
    }
}

