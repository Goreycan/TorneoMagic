 package TorneoMagic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TorneoMagic.DTO.MazoDTO;
import TorneoMagic.model.Jugador;
import TorneoMagic.model.Mazo;
import TorneoMagic.repository.JugadorRepository;
import TorneoMagic.repository.MazoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MazoService {

    @Autowired
    private MazoRepository mazoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    public List<MazoDTO> obtenerTodos() {
        return mazoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public MazoDTO buscarPorId(Long id) {
        Mazo mazo = mazoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡Mazo no encontrado en la biblioteca!"));
        return convertirADTO(mazo);
    }

    public Mazo guardar(Mazo mazo) {
        return mazoRepository.save(mazo);
    }

    public String asignarDueñoAMazo(Long mazoId, Long jugadorId) {
        Mazo mazo = mazoRepository.findById(mazoId)
            .orElseThrow(() -> new RuntimeException("Error: El Mazo no existe."));
        Jugador jugador = jugadorRepository.findById(jugadorId)
            .orElseThrow(() -> new RuntimeException("Error: El Jugador no existe."));
        
        mazo.setJugador(jugador); 
        mazoRepository.save(mazo);

        return "El mazo '" + mazo.getNombre() + "' ahora pertenece a: " + jugador.getNombre();
    }

    private MazoDTO convertirADTO(Mazo mazo) {
        MazoDTO dto = new MazoDTO();
        dto.setId(mazo.getId());
        dto.setNombre(mazo.getNombre());
        dto.setDescripcion(mazo.getDescripcion());
        
        if (mazo.getJugador() != null) {
            dto.setNombreJugador(mazo.getJugador().getNombre());
        } else {
            dto.setNombreJugador(" busca dueño");
        }    
        return dto;
    }
    
}

