package TorneoMagic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
@Entity
@Table(name = "mazo")
public class Mazo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mazo") // Ajustado al MER
    private Long id;

    @NotBlank(message = "El nombre del mazo es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre del mazo debe tener entre 3 y 50 caracteres")
    @Column(nullable = false, length = 50)
    private String nombre;

    //@ManyToOne (mapped = " ")
    @JoinColumn(name = "id_jugador", nullable = false) // FK según MER
    private Jugador jugador;

    
    //@OneToMany(mappedBy = "mazo")
    private List<CartaMazo> cartaMazos; 


    
}



