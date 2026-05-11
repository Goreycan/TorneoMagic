package TorneoMagic.controller;

import TorneoMagic.model.Participacion;
import TorneoMagic.service.ParticipacionService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/participaciones")
public class ParticipacionController {

    private final ParticipacionService participacionService;

    public ParticipacionController(
            ParticipacionService participacionService
    ) {
        this.participacionService = participacionService;
    }

    // =====================================
    // CRUD
    // =====================================

    @GetMapping
    public List<Participacion> listarParticipaciones() {

        return participacionService
                .listarParticipaciones();
    }

    @PostMapping
    public Participacion guardarParticipacion(
            @Valid @RequestBody Participacion participacion
    ) {

        return participacionService
                .guardarParticipacion(participacion);
    }

    @GetMapping("/{id}")
    public Participacion obtenerPorId(
            @PathVariable Long id
    ) {

        return participacionService
                .obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarParticipacion(
            @PathVariable Long id
    ) {

        participacionService
                .eliminarParticipacion(id);
    }

    // =====================================
    // RANKING
    // =====================================

    @GetMapping("/ranking")
    public List<Participacion> ranking() {

        return participacionService
                .generarRanking();
    }
}