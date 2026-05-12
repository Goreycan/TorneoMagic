package TorneoMagic.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Local_Organizador_DTO {
    Long id;
    Long localId;
    Long organizadorId;
    String cargo;
    LocalDate fechaAsignacion;
    Boolean estado;
}
