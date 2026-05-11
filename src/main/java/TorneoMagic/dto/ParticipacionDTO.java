package TorneoMagic.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipacionDTO {

    private Long id;

    private Long jugadorId;
    private String nombreJugador;

    private Long torneoId;
    private String nombreTorneo;

    private Integer rondaInscripcion;
}