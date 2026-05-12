package TorneoMagic.dto;


import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JugadorDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private List<String> nombreMazos;
    
}

