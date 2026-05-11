package TorneoMagic.service;

import TorneoMagic.DTO.TorneoDTO;
import TorneoMagic.model.Torneo;
import TorneoMagic.repository.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;

    // =====================================
    // OBTENER TODOS
    // =====================================

    public List<TorneoDTO> obtenerTodos() {
        return torneoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    // =====================================
    // GUARDAR
    // =====================================

    public Torneo guardarTorneo(Torneo torneo) {
        return torneoRepository.save(torneo);
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    public Torneo obtenerPorId(Long id) {
        return torneoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Torneo no encontrado"));
    }

    // =====================================
    // ELIMINAR
    // =====================================

    public void eliminarTorneo(Long id) {
        torneoRepository.deleteById(id);
    }

    // =====================================
    // VALIDAR TORNEO ACTIVO
    // =====================================

    public boolean torneoActivo(Torneo torneo) {
        return torneo.getEstado()
                .equalsIgnoreCase("ACTIVO");
    }

    // =====================================
    // FINALIZAR TORNEO
    // =====================================

    public void finalizarTorneo(Torneo torneo) {
        torneo.setEstado("FINALIZADO");
        torneoRepository.save(torneo);
    }

    // =====================================
    // CONVERTIR DTO
    // =====================================

    private TorneoDTO convertirADTO(Torneo torneo) {
        TorneoDTO dto =
                new TorneoDTO();
        dto.setId(torneo.getId());
        dto.setNombre(torneo.getNombre());
        dto.setFormato(torneo.getFormato());
        dto.setEstado(torneo.getEstado());
        dto.setFecha(torneo.getFecha());
        return dto;
    }
}