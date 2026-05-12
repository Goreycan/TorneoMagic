package TorneoMagic.dto;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalDTO{
    
    private Long id;
    private String nombre;
    private String direccion;
    private Integer capacidad;
    private Long comunaId;
}
