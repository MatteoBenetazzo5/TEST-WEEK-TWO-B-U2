package matteobenetazzo.testweektwobu2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Viaggio {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String destinazione;

    @Column(nullable = false)
    private LocalDate data;

    // Ho deciso che lo stato del viaggio può essere IN_PROGRAMMA o COMPLETATO, in postman ho messo tutti in programma giusto per essere piu comodo
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoViaggio stato;

    public Viaggio(String destinazione, LocalDate data, StatoViaggio stato) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = stato;
    }
}


