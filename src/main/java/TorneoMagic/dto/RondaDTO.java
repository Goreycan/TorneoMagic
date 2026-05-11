package TorneoMagic.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RondaDTO {

    private Long id;
    private Integer numeroRonda;
    private Long torneoId;
    private String nombreTorneo;
}