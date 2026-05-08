package TorneoMagic.model;

package TorneoMagic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "torneo")
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_torneo")
    private Long id;

    @NotBlank(message = "El nombre del torneo es obligatorio")
    private String nombre;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @NotBlank(message = "La ubicación es obligatoria")
    private String ubicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_local", nullable = false)
    private Local local;
}