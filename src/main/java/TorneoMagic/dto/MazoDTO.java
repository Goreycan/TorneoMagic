package TorneoMagic.dto;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MazoDTO {

    private Long id;

    private String nombre;

    private String descripcion;

    private Integer idJugador;

    private String nombreJugador;

    private List<String> nombresCartas;
}