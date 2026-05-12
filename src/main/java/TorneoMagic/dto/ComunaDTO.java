package TorneoMagic.dto;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComunaDTO
{
   private Long id;
   private String nombre;
   private Long regionId;

}
