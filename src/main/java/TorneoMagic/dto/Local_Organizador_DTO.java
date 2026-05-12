package TorneoMagic.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Local_Organizador_DTO {
   private Long id;
   private Long localId;
   private Long organizadorId;
   private String cargo;
   private LocalDate fechaAsignacion;
   private Boolean estado;
}
