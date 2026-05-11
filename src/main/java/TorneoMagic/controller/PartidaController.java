package TorneoMagic.controller;

import TorneoMagic.model.Jugador;
import TorneoMagic.model.Partida;
import TorneoMagic.model.Ronda;
import TorneoMagic.service.PartidaService;
import TorneoMagic.service.RondaService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/partidas")
public class PartidaController {

    private final PartidaService partidaService;
    private final RondaService rondaService;

    public PartidaController(
            PartidaService partidaService,
            RondaService rondaService
    ) {
        this.partidaService = partidaService;
        this.rondaService = rondaService;
    }

    // =====================================
    // CRUD
    // =====================================

    @GetMapping
    public List<Partida> listarPartidas() {

        return partidaService.listarPartidas();
    }

    @PostMapping
    public Partida guardarPartida(
            @Valid @RequestBody Partida partida
    ) {

        return partidaService.guardarPartida(partida);
    }

    // =====================================
    // GENERAR PARTIDAS
    // =====================================

    @PostMapping("/generar/{rondaId}")
    public List<Partida> generarPartidas(
            @Valid
            @PathVariable Long rondaId,
            @RequestBody List<Jugador> jugadores
    ) {

        Ronda ronda =
                rondaService.obtenerPorId(rondaId);

        return partidaService.generarPartidas(
                ronda,
                jugadores
        );
    }

    // =====================================
    // FINALIZAR PARTIDA
    // =====================================

    @PutMapping("/finalizar/{id}")
    public void finalizarPartida(
            @Valid @PathVariable Long id
    ) {

        Partida partida =
                partidaService.obtenerPorId(id);

        partidaService.finalizarPartida(partida);
    }
}