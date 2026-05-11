package TorneoMagic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TorneoMagic.dto.JugadorDTO;
import TorneoMagic.model.Jugador;
import TorneoMagic.model.Mazo;
import TorneoMagic.repository.JugadorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    public List<JugadorDTO> obtenerTodos() {
        return jugadorRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public JugadorDTO buscarPorId(Long id) {
        Jugador jugador = jugadorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡El duelista no existe en nuestros registros!"));
        return convertirADTO(jugador);
    }

    public String eliminar(Long id) {
        try {
            Jugador jugador = jugadorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException(" El jugador con ID " + id + " no existe."));
            jugadorRepository.delete(jugador);
            return "El duelista '" + jugador.getNombre() + "' ha sido descalificado del torneo.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Jugador guardar(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    private JugadorDTO convertirADTO(Jugador jugador) {
        JugadorDTO dto = new JugadorDTO();
        dto.setId(jugador.getId());
        dto.setNombre(jugador.getNombre());

        if (jugador.getMazos() != null && !jugador.getMazos().isEmpty()) {
            dto.setNombreMazos(jugador.getMazos().stream()
                    .map(Mazo::getNombre)
                    .toList());
        } else {
            dto.setNombreMazos(new ArrayList<>()); 
        }
        
        return dto;
    }
    //prueba

    
}
