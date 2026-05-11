package TorneoMagic.service;

import TorneoMagic.model.Participacion;
import TorneoMagic.model.Partida;
import TorneoMagic.model.Resultado;
import TorneoMagic.repository.ParticipacionRepository;
import TorneoMagic.repository.PartidaRepository;
import TorneoMagic.repository.ResultadoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ResultadoService {

    private final ResultadoRepository resultadoRepository;
    private final ParticipacionRepository participacionRepository;
    private final PartidaRepository partidaRepository;

    public ResultadoService(
            ResultadoRepository resultadoRepository,
            ParticipacionRepository participacionRepository,
            PartidaRepository partidaRepository
    ) {
        this.resultadoRepository = resultadoRepository;
        this.participacionRepository = participacionRepository;
        this.partidaRepository = partidaRepository;
    }

    // =====================================
    // CRUD BÁSICO
    // =====================================

    public List<Resultado> listarResultados() {
        return resultadoRepository.findAll();
    }

    public Resultado guardarResultado(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

    public Resultado obtenerPorId(Long id) {
        return resultadoRepository.findById(id).orElse(null);
    }

    public void eliminarResultado(Long id) {
        resultadoRepository.deleteById(id);
    }

    // =====================================
    // LÓGICA DE NEGOCIO
    // =====================================

    public Resultado registrarResultado(Resultado resultado) {

        // ==============================
        // VALIDAR PARTIDA
        // ==============================

        Partida partida = resultado.getPartida();

        if (partida == null) {
            throw new RuntimeException(
                    "La partida es obligatoria"
            );
        }

        // ==============================
        // VALIDAR GANADOR
        // ==============================

        if (resultado.getGanador() == null) {
            throw new RuntimeException(
                    "Debe existir un ganador"
            );
        }

        // ==============================
        // GUARDAR RESULTADO
        // ==============================

        Resultado resultadoGuardado =
                resultadoRepository.save(resultado);

        // ==============================
        // FINALIZAR PARTIDA
        // ==============================

        partida.setEstado("FINALIZADA");

        partidaRepository.save(partida);

        // ==============================
        // ACTUALIZAR PUNTOS PARTICIPACIÓN
        // ==============================

        List<Participacion> participaciones =
                participacionRepository.findAll();

        for (Participacion participacion : participaciones) {

            // GANADOR = 3 PUNTOS
            if (participacion.getJugador().getId()
                    .equals(resultado.getGanador().getId())) {

                participacion.setPuntos(
                        participacion.getPuntos() + 3
                );

                participacionRepository.save(participacion);
            }
        }

        return resultadoGuardado;
    }

    // =====================================
    // GENERAR RANKING
    // =====================================

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