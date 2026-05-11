package TorneoMagic.service;

import TorneoMagic.model.Participacion;
import TorneoMagic.repository.ParticipacionRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ParticipacionService {

    private final ParticipacionRepository participacionRepository;

    public ParticipacionService(
            ParticipacionRepository participacionRepository
    ) {
        this.participacionRepository = participacionRepository;
    }

    // =====================================
    // CRUD
    // =====================================

    public List<Participacion> listarParticipaciones() {
        return participacionRepository.findAll();
    }

    public Participacion guardarParticipacion(
            Participacion participacion
    ) {
        return participacionRepository.save(participacion);
    }

    public Participacion obtenerPorId(Long id) {
        return participacionRepository.findById(id).orElse(null);
    }

    public void eliminarParticipacion(Long id) {
        participacionRepository.deleteById(id);
    }

    // =====================================
    // LÓGICA DE NEGOCIO
    // =====================================

    public void sumarPuntos(
            Participacion participacion,
            Integer puntos
    ) {

        if (participacion.getPuntos() == null) {
            participacion.setPuntos(0);
        }

        participacion.setPuntos(
                participacion.getPuntos() + puntos
        );

        participacionRepository.save(participacion);
    }

    public List<Participacion> generarRanking() {

        List<Participacion> ranking =
                participacionRepository.findAll();

        ranking.sort(
                Comparator.comparing(
                        Participacion::getPuntos
                ).reversed()
        );

        return ranking;
    }
}