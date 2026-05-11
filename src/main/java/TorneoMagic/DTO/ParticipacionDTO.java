package TorneoMagic.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipacionDTO {

    private Long id;

    private Long jugadorId;

    private Long torneoId;

    private Integer puntos;

    private Integer posicion;
}