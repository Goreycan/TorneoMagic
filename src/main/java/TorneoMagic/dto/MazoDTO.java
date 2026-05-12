package TorneoMagic.dto;
import java.util.List;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MazoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String nombreJugador; 

    private Long idJugador;
    private List<String> nombresCartas;
}

