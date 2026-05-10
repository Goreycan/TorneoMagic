package TorneoMagic.DTO;


import java.util.List;

import lombok.Data;

@Data
public class JugadorDTO {
    private Long id;
    private String nombre;
    private List<String> nombreMazos;
    
}

