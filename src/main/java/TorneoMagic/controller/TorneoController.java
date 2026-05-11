package TorneoMagic.controller;

import TorneoMagic.model.Torneo;
import TorneoMagic.service.TorneoService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/torneos")
public class TorneoController {

    private final TorneoService torneoService;

    public TorneoController(
            TorneoService torneoService
    ) {
        this.torneoService = torneoService;
    }

    // =====================================
    // CRUD
    // =====================================

    @GetMapping
    public List<Torneo> listarTorneos() {
        return torneoService.listarTorneos();
    }

    @PostMapping
    public Torneo guardarTorneo(
            @Valid @RequestBody Torneo torneo
    ) {
        return torneoService.guardarTorneo(torneo);
    }

    @GetMapping("/{id}")
    public Torneo obtenerPorId(
            @PathVariable Long id
    ) {
        return torneoService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarTorneo(
            @PathVariable Long id
    ) {
        torneoService.eliminarTorneo(id);
    }

    // =====================================
    // LÓGICA NEGOCIO
    // =====================================

    @PutMapping("/finalizar/{id}")
    public void finalizarTorneo(
            @Valid @PathVariable Long id
    ) {

        Torneo torneo =
                torneoService.obtenerPorId(id);

        torneoService.finalizarTorneo(torneo);
    }
}