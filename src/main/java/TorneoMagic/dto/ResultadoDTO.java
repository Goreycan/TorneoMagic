package TorneoMagic.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoDTO {

    private Long id;

    private Long partidaId;

    private String mesa;

    private Long ganadorId;
    private String nombreGanador;

    private Integer puntajeJugador1;
    private Integer puntajeJugador2;
    private Integer puntajeJugador3;
    private Integer puntajeJugador4;
    private Integer puntajeJugador5;

    private Integer posicionJugador1;
    private Integer posicionJugador2;
    private Integer posicionJugador3;
    private Integer posicionJugador4;
    private Integer posicionJugador5;

    private String observaciones;
}