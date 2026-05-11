package TorneoMagic.DTO;


import java.util.List;

import lombok.Data;

@Data
public class JugadorDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private List<String> nombreMazos;
    
}

