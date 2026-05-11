package TorneoMagic.service;

import TorneoMagic.model.Torneo;
import TorneoMagic.repository.TorneoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TorneoService {

    private final TorneoRepository torneoRepository;

    public TorneoService(TorneoRepository torneoRepository) {
        this.torneoRepository = torneoRepository;
    }

    // LISTAR TODOS LOS TORNEOS
    public List<Torneo> listarTorneos() {
        return torneoRepository.findAll();
    }

    // GUARDAR TORNEO
    public Torneo guardarTorneo(Torneo torneo) {
        return torneoRepository.save(torneo);
    }

    // BUSCAR POR ID
    public Torneo obtenerPorId(Long id) {
        return torneoRepository.findById(id).orElse(null);
    }

    // ELIMINAR TORNEO
    public void eliminarTorneo(Long id) {
        torneoRepository.deleteById(id);
    }
}