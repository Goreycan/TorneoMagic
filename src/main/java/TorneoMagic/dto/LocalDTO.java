package TorneoMagic.dto;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalDTO{
    Long id;
    String nombre;
    String direccion;
    Integer capacidad;
    Long comunaId;
}
