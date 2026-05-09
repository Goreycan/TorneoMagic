package TorneoMagic.service;

import TorneoMagic.model.Participacion;
import TorneoMagic.repository.ParticipacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipacionService {

    private final ParticipacionRepository participacionRepository;

    public ParticipacionService(ParticipacionRepository participacionRepository) {
        this.participacionRepository = participacionRepository;
    }

    public List<Participacion> listarParticipaciones() {
        return participacionRepository.findAll();
    }

    public Participacion guardarParticipacion(Participacion participacion) {
        return participacionRepository.save(participacion);
    }

    public Participacion obtenerPorId(Long id) {
        return participacionRepository.findById(id).orElse(null);
    }

    public void eliminarParticipacion(Long id) {
        participacionRepository.deleteById(id);
    }
}