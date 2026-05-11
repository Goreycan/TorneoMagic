package TorneoMagic.service;

import TorneoMagic.DTO.ParticipacionDTO;
import TorneoMagic.model.Participacion;
import TorneoMagic.repository.ParticipacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class ParticipacionService {

    @Autowired
    private ParticipacionRepository participacionRepository;

    // =====================================
    // OBTENER TODAS
    // =====================================

    public List<ParticipacionDTO> obtenerTodas() {
        return participacionRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    // =====================================
    // GUARDAR
    // =====================================

    public Participacion guardarParticipacion(
            Participacion participacion
    ) {
        return participacionRepository.save(participacion);
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    public Participacion obtenerPorId(Long id) {
        return participacionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Participación no encontrada"
                        )
                );
    }

    // =====================================
    // ELIMINAR
    // =====================================

    public void eliminarParticipacion(Long id) {
        participacionRepository.deleteById(id);
    }

    // =====================================
    // SUMAR PUNTOS
    // =====================================

    public void sumarPuntos(
            Participacion participacion,
            Integer puntos
    ) {

        if (participacion.getPuntos() == null) {
            participacion.setPuntos(0);
        }

        participacion.setPuntos(
                participacion.getPuntos() + puntos
        );

        participacionRepository.save(participacion);
    }

    // =====================================
    // GENERAR RANKING
    // =====================================

    public List<ParticipacionDTO> generarRanking() {
        return participacionRepository.findAll()
                .stream()
                .sorted(
                        Comparator.comparing(
                                Participacion::getPuntos
                        ).reversed()
                )
                .map(this::convertirADTO)
                .toList();
    }

    // =====================================
    // CONVERTIR DTO
    // =====================================

    private ParticipacionDTO convertirADTO(
            Participacion participacion
    ) {
        ParticipacionDTO dto =
                new ParticipacionDTO();
        dto.setId(participacion.getId());
        dto.setPuntos(
                participacion.getPuntos()
        );
        if (participacion.getJugador() != null) {
            dto.setNombreJugador(
                    participacion.getJugador()
                            .getNombre()
            );
        }
        if (participacion.getTorneo() != null) {
            dto.setNombreTorneo(
                    participacion.getTorneo()
                            .getNombre()
            );
        }
        return dto;
    }
}