package TorneoMagic.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TorneoMagic.DTO.JugadorDTO;
import TorneoMagic.model.Jugador;
import TorneoMagic.model.Mazo;
import TorneoMagic.repository.JugadorRepository;

@Service
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
                .orElseThrow(() -> new RuntimeException("¡El jugador no existe en los registros!"));
        return convertirADTO(jugador);
    }

    public Jugador guardarJugador(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public String eliminar(Long id) {
        try {
            Jugador jugador = jugadorRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El jugador con ID " + id + " no existe."));
            jugadorRepository.delete(jugador);
            return "El jugador '" + jugador.getNombre() + "' ha sido retirado del torneo exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Jugador actualizarJugador(Long id, Jugador jugadorActualizado) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡El jugador no existe!"));
        
        if (jugadorActualizado.getNombre() != null) {
            jugador.setNombre(jugadorActualizado.getNombre());
        }
        
        return jugadorRepository.save(jugador);
    }

    
    private JugadorDTO convertirADTO(Jugador jugador) {
        JugadorDTO dto = new JugadorDTO();
        dto.setId(jugador.getId());
        dto.setNombre(jugador.getNombre());

        if (jugador.getMazos() != null) {
            dto.setNombreMazos(jugador.getMazos().stream()
                    .map(Mazo::getNombre)
                    .toList());
        } else {
            dto.setNombreMazos(new ArrayList<>());
        }

        return dto;
    }

    
}
