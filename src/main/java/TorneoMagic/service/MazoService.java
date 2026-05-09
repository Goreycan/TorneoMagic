package TorneoMagic.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TorneoMagic.DTO.MazoDTO;
import TorneoMagic.model.Jugador;
import TorneoMagic.model.Mazo;
import TorneoMagic.repository.JugadorRepository;
import TorneoMagic.repository.MazoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MazoService {

    @Autowired
    private MazoRepository mazoRepository;

    @Autowired
    private JugadorRepository jugadorRepository;

    public MazoDTO guardarMazo(MazoDTO mazoDTO) {
        Mazo mazo = new Mazo();
        mazo.setNombre(mazoDTO.getNombre());

        // Buscamos al jugador por el ID que viene en el DTO
        if (mazoDTO.getIdJugador() != null) {
            Jugador jugador = jugadorRepository.findById(mazoDTO.getIdJugador())
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
            mazo.setJugador(jugador);
        }

        Mazo mazoGuardado = mazoRepository.save(mazo);
        return convertirADTO(mazoGuardado);
    }

    public List<MazoDTO> obtenerTodos() {
        return mazoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private MazoDTO convertirADTO(Mazo mazo) {
        MazoDTO dto = new MazoDTO();
        dto.setId(mazo.getId());
        dto.setNombre(mazo.getNombre());
        if (mazo.getJugador() != null) {
            dto.setIdJugador(mazo.getJugador().getId()); // Solo el ID, ¡adiós bucles!
        }
        return dto;
    }
}

