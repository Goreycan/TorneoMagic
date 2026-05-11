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
    // Jugadores de la partida
    private Long jugador1Id;
    private Long jugador2Id;
    private Long jugador3Id;
    private Long jugador4Id;
    private Long jugador5Id;
}