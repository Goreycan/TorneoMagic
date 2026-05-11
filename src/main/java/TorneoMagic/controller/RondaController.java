package TorneoMagic.controller;

import TorneoMagic.DTO.RondaDTO;
import TorneoMagic.model.Ronda;
import TorneoMagic.service.RondaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rondas")
public class RondaController {

    @Autowired
    private RondaService rondaService;

    @GetMapping
    public ResponseEntity<List<RondaDTO>> listarTodas() {
        List<RondaDTO> rondas =
                rondaService.obtenerTodas();
        if (rondas.isEmpty()) {
            return new ResponseEntity<>(
                    HttpStatus.NO_CONTENT
            );
        }
        return new ResponseEntity<>(
                rondas,
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ronda> buscarPorId(@PathVariable Long id) {
        try {
            Ronda ronda =
                    rondaService.obtenerPorId(id);
            return new ResponseEntity<>(
                    ronda,
                    HttpStatus.OK
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping
    public ResponseEntity<Ronda> guardarRonda(@Valid @RequestBody Ronda ronda) {
        try {
            Ronda guardada =
                    rondaService.guardarRonda(ronda);
            return new ResponseEntity<>(
                    guardada,
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRonda(@PathVariable Long id) {
        try {
            rondaService.eliminarRonda(id);
            return new ResponseEntity<>(
                    "Ronda eliminada",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Ronda no encontrada",
                    HttpStatus.NOT_FOUND
            );
        }
    }
}