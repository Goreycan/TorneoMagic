package TorneoMagic.dto;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizadorDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
