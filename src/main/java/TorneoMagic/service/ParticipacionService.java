package TorneoMagic.service;

import TorneoMagic.dto.ParticipacionDTO;
import TorneoMagic.model.Participacion;
import TorneoMagic.repository.ParticipacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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

    public Participacion guardarParticipacion(Participacion participacion) {
        return participacionRepository.save(participacion);
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    public Participacion obtenerPorId(Long id) {
        return participacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participación no encontrada"));
    }

    // =====================================
    // ELIMINAR
    // =====================================

    public void eliminarParticipacion(Long id) {
        participacionRepository.deleteById(id);
    }

    // =====================================
    // LISTAR PARTICIPACIONES
    // =====================================

    public List<Participacion> listarParticipaciones() {
        return participacionRepository.findAll();
    }

    // =====================================
    // CONVERTIR DTO
    // =====================================

    private ParticipacionDTO convertirADTO(Participacion participacion) {

        ParticipacionDTO dto = new ParticipacionDTO();

        dto.setId(participacion.getId());

        if (participacion.getJugador() != null) {
            dto.setJugadorId(Long.valueOf(participacion.getJugador().getId()));
            dto.setNombreJugador(participacion.getJugador().getNombre());
        }

        if (participacion.getTorneo() != null) {
            dto.setTorneoId(Long.valueOf(participacion.getTorneo().getId()));
            dto.setNombreTorneo(participacion.getTorneo().getNombre());
        }

        dto.setRondaInscripcion(participacion.getRondaInscripcion());

        return dto;
    }

    // =====================================
    // GENERAR RANKING
    // =====================================

public List<ParticipacionDTO> generarRanking() {
    return participacionRepository.findAll()
            .stream()
            .map(this::convertirADTO)
            .toList();
}
}