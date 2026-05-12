package TorneoMagic.dto;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizadorDTO {
    Long id;
    String nombre;
    String apellido;
    String email;
    String telefono;
}
