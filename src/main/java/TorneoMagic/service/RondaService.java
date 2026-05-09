package TorneoMagic.service;

import TorneoMagic.model.Ronda;
import TorneoMagic.repository.RondaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RondaService {

    private final RondaRepository rondaRepository;

    public RondaService(RondaRepository rondaRepository) {
        this.rondaRepository = rondaRepository;
    }

    public List<Ronda> listarRondas() {
        return rondaRepository.findAll();
    }

    public Ronda guardarRonda(Ronda ronda) {
        return rondaRepository.save(ronda);
    }

    public Ronda obtenerPorId(Long id) {
        return rondaRepository.findById(id).orElse(null);
    }

    public void eliminarRonda(Long id) {
        rondaRepository.deleteById(id);
    }
}