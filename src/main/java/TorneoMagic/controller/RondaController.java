package TorneoMagic.controller;

import TorneoMagic.model.Ronda;
import TorneoMagic.model.Torneo;
import TorneoMagic.service.RondaService;
import TorneoMagic.service.TorneoService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rondas")
public class RondaController {

    private final RondaService rondaService;
    private final TorneoService torneoService;

    public RondaController(
            RondaService rondaService,
            TorneoService torneoService
    ) {
        this.rondaService = rondaService;
        this.torneoService = torneoService;
    }

    // =====================================
    // CRUD
    // =====================================

    @GetMapping
    public List<Ronda> listarRondas() {

        return rondaService.listarRondas();
    }

    @PostMapping
    public Ronda guardarRonda(
            @Valid @RequestBody Ronda ronda
    ) {

        return rondaService.guardarRonda(ronda);
    }

    // =====================================
    // GENERAR RONDAS
    // =====================================

    @PostMapping("/generar")
    public List<Ronda> generarRondas(
            @Valid
            @RequestParam Long torneoId,
            @RequestParam Integer jugadores
    ) {

        Torneo torneo =
                torneoService.obtenerPorId(torneoId);

        return rondaService.crearRondas(
                torneo,
                jugadores
        );
    }
}