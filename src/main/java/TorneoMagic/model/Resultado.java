package TorneoMagic.model;

package TorneoMagic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "resultado")
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resultado")
    private Long id;

    @NotNull(message = "La partida es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partida", nullable = false)
    private Partida partida;

    @NotNull(message = "Debe existir un ganador")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ganador", nullable = false)
    private Jugador ganador;

    @Column(name = "puntaje_jugador1")
    private Integer puntajeJugador1;

    @Column(name = "puntaje_jugador2")
    private Integer puntajeJugador2;

    private String observaciones;
}