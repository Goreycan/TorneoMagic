package TorneoMagic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "partida")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_partida")
    private Long id;

    @NotBlank(message = "La mesa es obligatoria")
    private String mesa;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "La ronda es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ronda", nullable = false)
    private Ronda ronda;

    // ==============================
    // CANTIDAD DE JUGADORES
    // ==============================

    @NotNull(message = "La cantidad de jugadores es obligatoria")
    @Min(value = 3, message = "La partida debe tener mínimo 3 jugadores")
    @Max(value = 5, message = "La partida permite máximo 5 jugadores")
    @Column(name = "cantidad_jugadores", nullable = false)
    private Integer cantidadJugadores;

    // ==============================
    // JUGADORES OBLIGATORIOS
    // ==============================

    @NotNull(message = "Jugador 1 obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador1", nullable = false)
    private Jugador jugador1;

    @NotNull(message = "Jugador 2 obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador2", nullable = false)
    private Jugador jugador2;

    @NotNull(message = "Jugador 3 obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador3", nullable = false)
    private Jugador jugador3;

    // ==============================
    // JUGADORES OPCIONALES
    // ==============================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador4")
    private Jugador jugador4;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador5")
    private Jugador jugador5;
}