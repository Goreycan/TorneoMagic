package TorneoMagic.DTOs;

import java.time.LocalDate;

public record Local_Organizador_DTO(
    Long id,
    Long localId,
    Long organizadorId,
    String cargo,
    LocalDate fechaAsignacion,
    Boolean estado
){
}
