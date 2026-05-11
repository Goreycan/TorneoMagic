package TorneoMagic.dto;

import lombok.Data;
import java.util.List;

@Data
public class CartaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String rareza;
    private Integer costo;
    
    private List<String> nombresMazosDondeAparece;
}

