package TorneoMagic.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartaMazoDTO {
    private Long id;
    private Integer cantidad;
    private String nombreMazo;  
    private String nombreCarta; 
}


