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

    // =====================================
    // CRUD BÁSICO
    // =====================================

    public List<Torneo> listarTorneos() {
        return torneoRepository.findAll();
    }

    public Torneo guardarTorneo(Torneo torneo) {
        return torneoRepository.save(torneo);
    }

    public Torneo obtenerPorId(Long id) {
        return torneoRepository.findById(id).orElse(null);
    }

    public void eliminarTorneo(Long id) {
        torneoRepository.deleteById(id);
    }

    // =====================================
    // LÓGICA DE NEGOCIO
    // =====================================

    public boolean torneoActivo(Torneo torneo) {

        return torneo.getEstado()
                .equalsIgnoreCase("ACTIVO");
    }

    public void finalizarTorneo(Torneo torneo) {

        torneo.setEstado("FINALIZADO");

        torneoRepository.save(torneo);
    }
}