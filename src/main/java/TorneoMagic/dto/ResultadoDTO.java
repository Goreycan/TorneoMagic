package TorneoMagic.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoDTO {

    private Long id;
    // Partida relacionada
    private Long partidaId;
    // Ganador
    private Long ganadorId;
    // Puntajes jugadores
    private Integer puntosJugador1;
    private Integer puntosJugador2;
    private Integer puntosJugador3;
    private Integer puntosJugador4;
    private Integer puntosJugador5;
    // Posiciones finales opcionales
    private Integer posicionJugador1;
    private Integer posicionJugador2;
    private Integer posicionJugador3;
    private Integer posicionJugador4;
    private Integer posicionJugador5;
}