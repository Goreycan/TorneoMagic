package TorneoMagic.service;

import TorneoMagic.model.Ronda;
import TorneoMagic.model.Torneo;
import TorneoMagic.repository.RondaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RondaService {

    private final RondaRepository rondaRepository;

    public RondaService(RondaRepository rondaRepository) {
        this.rondaRepository = rondaRepository;
    }

    // =====================================
    // CRUD BÁSICO
    // =====================================

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

    // =====================================
    // LÓGICA DE NEGOCIO
    // =====================================

    // CALCULAR CANTIDAD DE RONDAS
    public Integer calcularCantidadRondas(Integer jugadores) {

        if (jugadores <= 16) {
            return 3;
        }

        if (jugadores <= 32) {
            return 4;
        }

        return 5;
    }

    // CREAR RONDAS AUTOMÁTICAMENTE
    public List<Ronda> crearRondas(
            Torneo torneo,
            Integer jugadores
    ) {

        Integer cantidadRondas =
                calcularCantidadRondas(jugadores);

        List<Ronda> rondas = new ArrayList<>();

        for (int i = 1; i <= cantidadRondas; i++) {

            Ronda ronda = Ronda.builder()
                    .numeroRonda(i)
                    .torneo(torneo)
                    .build();

            rondas.add(
                    rondaRepository.save(ronda)
            );
        }

        return rondas;
    }
}