package TorneoMagic.service;

import TorneoMagic.DTO.RondaDTO;
import TorneoMagic.model.Ronda;
import TorneoMagic.model.Torneo;
import TorneoMagic.repository.RondaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RondaService {

    @Autowired
    private RondaRepository rondaRepository;

    // =====================================
    // OBTENER TODAS
    // =====================================

    public List<RondaDTO> obtenerTodas() {
        return rondaRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    // =====================================
    // GUARDAR
    // =====================================

    public Ronda guardarRonda(Ronda ronda) {
        return rondaRepository.save(ronda);
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    public Ronda obtenerPorId(Long id) {
        return rondaRepository.findById(id).orElseThrow(() -> new RuntimeException("Ronda no encontrada")
                );
    }

    // =====================================
    // ELIMINAR
    // =====================================

    public void eliminarRonda(Long id) {
        rondaRepository.deleteById(id);
    }

    // =====================================
    // CALCULAR RONDAS
    // =====================================

    public Integer calcularCantidadRondas(Integer jugadores) {
        if (jugadores <= 32) {
            return 4;
        }
        return 5;
    }

    // =====================================
    // CREAR RONDAS
    // =====================================

    public List<Ronda> crearRondas(Torneo torneo,Integer jugadores) {
        Integer cantidadRondas = calcularCantidadRondas(jugadores);
        List<Ronda> rondas = new ArrayList<>();
        for (int i = 1;i <= cantidadRondas;i++) 
        {
            Ronda ronda = Ronda.builder().numeroRonda(i).torneo(torneo).build();rondas.add(rondaRepository.save(ronda));
        }
        return rondas;
    }

    // =====================================
    // CONVERTIR DTO
    // =====================================

    private RondaDTO convertirADTO(Ronda ronda) {
        RondaDTO dto = new RondaDTO();
        dto.setId(ronda.getId());
        dto.setNumeroRonda(ronda.getNumeroRonda());
        if (ronda.getTorneo() != null) 
        {
            dto.setNombreTorneo(ronda.getTorneo().getNombre());
        }
        return dto;
    }
}