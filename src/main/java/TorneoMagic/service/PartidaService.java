package TorneoMagic.service;

import TorneoMagic.model.Jugador;
import TorneoMagic.model.Partida;
import TorneoMagic.model.Ronda;
import TorneoMagic.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PartidaService {

    private final PartidaRepository partidaRepository;

    public PartidaService(
            PartidaRepository partidaRepository
    ) {
        this.partidaRepository = partidaRepository;
    }

    // =====================================
    // CRUD
    // =====================================

    public List<Partida> listarPartidas() {
        return partidaRepository.findAll();
    }

    public Partida guardarPartida(Partida partida) {
        return partidaRepository.save(partida);
    }

    public Partida obtenerPorId(Long id) {
        return partidaRepository.findById(id).orElse(null);
    }

    public void eliminarPartida(Long id) {
        partidaRepository.deleteById(id);
    }

    // =====================================
    // LÓGICA DE NEGOCIO
    // =====================================

    public List<Partida> generarPartidas(
            Ronda ronda,
            List<Jugador> jugadores
    ) {

        Collections.shuffle(jugadores);

        List<Partida> partidas = new ArrayList<>();

        int mesa = 1;

        for (int i = 0; i < jugadores.size(); i += 4) {

            List<Jugador> grupo =
                    jugadores.subList(
                            i,
                            Math.min(i + 4, jugadores.size())
                    );

            if (grupo.size() < 3) {
                break;
            }

            Partida partida = Partida.builder()
                    .mesa("Mesa " + mesa)
                    .estado("PENDIENTE")
                    .ronda(ronda)
                    .cantidadJugadores(grupo.size())
                    .jugador1(grupo.get(0))
                    .jugador2(grupo.get(1))
                    .jugador3(grupo.get(2))
                    .jugador4(
                            grupo.size() >= 4
                                    ? grupo.get(3)
                                    : null
                    )
                    .jugador5(
                            grupo.size() == 5
                                    ? grupo.get(4)
                                    : null
                    )
                    .build();

            partidas.add(
                    partidaRepository.save(partida)
            );

            mesa++;
        }

        return partidas;
    }

    public void finalizarPartida(Partida partida) {

        partida.setEstado("FINALIZADA");

        partidaRepository.save(partida);
    }
}