package TorneoMagic.dto;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartaDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String rareza;
    private Integer costo;
    
    private List<String> nombresMazosDondeAparece;
}

