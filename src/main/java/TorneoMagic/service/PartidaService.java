package TorneoMagic.service;

import TorneoMagic.DTO.PartidaDTO;
import TorneoMagic.model.Jugador;
import TorneoMagic.model.Partida;
import TorneoMagic.model.Ronda;
import TorneoMagic.repository.PartidaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    // =====================================
    // OBTENER TODAS
    // =====================================

    public List<PartidaDTO> obtenerTodas() {
        return partidaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    // =====================================
    // GUARDAR
    // =====================================

    public Partida guardarPartida(
            Partida partida
    ) {
        return partidaRepository.save(partida);
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    public Partida obtenerPorId(Long id) {
        return partidaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Partida no encontrada"
                        )
                );
    }

    // =====================================
    // ELIMINAR
    // =====================================

    public void eliminarPartida(Long id) {
        partidaRepository.deleteById(id);
    }

    // =====================================
    // GENERAR PARTIDAS
    // =====================================

    public List<Partida> generarPartidas(
            Ronda ronda,
            List<Jugador> jugadores
    ) {

        Collections.shuffle(jugadores);
        List<Partida> partidas =
                new ArrayList<>();
        int mesa = 1;

        for (int i = 0;
             i < jugadores.size();
             i += 4) {
            List<Jugador> grupo =
                    jugadores.subList(
                            i,
                            Math.min(
                                    i + 4,
                                    jugadores.size()
                            )
                    );
            if (grupo.size() < 3) {
                break;
            }
            Partida partida = Partida.builder()
                    .mesa("Mesa " + mesa)
                    .estado("PENDIENTE")
                    .ronda(ronda)
                    .cantidadJugadores(
                            grupo.size()
                    )
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

    // =====================================
    // FINALIZAR PARTIDA
    // =====================================

    public void finalizarPartida(
            Partida partida
    ) {
        partida.setEstado(
                "FINALIZADA"
        );
        partidaRepository.save(partida);
    }

    // =====================================
    // CONVERTIR DTO
    // =====================================

    private PartidaDTO convertirADTO(
            Partida partida
    ) {

        PartidaDTO dto =
                new PartidaDTO();
        dto.setId(partida.getId());
        dto.setMesa(partida.getMesa());
        dto.setEstado(partida.getEstado());
        dto.setCantidadJugadores(partida.getCantidadJugadores()
        );
        if (partida.getRonda() != null) {
            dto.setNumeroRonda(
                    partida.getRonda()
                            .getNumeroRonda()
            );
        }
        if (partida.getJugador1() != null) {
            dto.setJugador1(
                    partida.getJugador1()
                            .getNombre()
            );
        }

        if (partida.getJugador2() != null) {
            dto.setJugador2(
                    partida.getJugador2()
                            .getNombre()
            );
        }

        if (partida.getJugador3() != null) {
            dto.setJugador3(
                    partida.getJugador3()
                            .getNombre()
            );
        }

        if (partida.getJugador4() != null) {
            dto.setJugador4(
                    partida.getJugador4()
                            .getNombre()
            );
        }

        if (partida.getJugador5() != null) {
            dto.setJugador5(
                    partida.getJugador5()
                            .getNombre()
            );
        }
        return dto;
    }
}