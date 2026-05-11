package TorneoMagic.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartidaDTO {

    private Long id;
    private String mesa;
    private String estado;
    private Integer cantidadJugadores;
    private Long rondaId;
    private Integer numeroRonda;
    // Jugadores de la partida
    private String jugador1;
    private String jugador2;
    private String jugador3;
    private String jugador4;
    private String jugador5;
}