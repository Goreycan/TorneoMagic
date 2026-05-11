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

    // =====================================
    // PARTIDA
    // =====================================

    @NotNull(message = "La partida es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_partida", nullable = false)
    private Partida partida;

    // =====================================
    // GANADOR
    // =====================================

    @NotNull(message = "Debe existir un ganador")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ganador", nullable = false)
    private Jugador ganador;

    // =====================================
    // PUNTAJES
    // =====================================

    @Column(name = "puntaje_jugador1")
    private Integer puntajeJugador1;

    @Column(name = "puntaje_jugador2")
    private Integer puntajeJugador2;

    @Column(name = "puntaje_jugador3")
    private Integer puntajeJugador3;

    @Column(name = "puntaje_jugador4")
    private Integer puntajeJugador4;

    @Column(name = "puntaje_jugador5")
    private Integer puntajeJugador5;

    // =====================================
    // POSICIONES
    // =====================================

    @Column(name = "posicion_jugador1")
    private Integer posicionJugador1;

    @Column(name = "posicion_jugador2")
    private Integer posicionJugador2;

    @Column(name = "posicion_jugador3")
    private Integer posicionJugador3;

    @Column(name = "posicion_jugador4")
    private Integer posicionJugador4;

    @Column(name = "posicion_jugador5")
    private Integer posicionJugador5;

    // =====================================
    // OBSERVACIONES
    // =====================================

    @Column(length = 300)
    private String observaciones;
}