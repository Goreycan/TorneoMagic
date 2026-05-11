package TorneoMagic.controller;

import TorneoMagic.DTO.ParticipacionDTO;
import TorneoMagic.model.Participacion;
import TorneoMagic.service.ParticipacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/participaciones")
public class ParticipacionController {

    @Autowired
    private ParticipacionService participacionService;

    @GetMapping
    public ResponseEntity<List<ParticipacionDTO>> listarTodas() {
        List<ParticipacionDTO> participaciones =
                participacionService.obtenerTodas();
        if (participaciones.isEmpty()) {
            return new ResponseEntity<>(
                    HttpStatus.NO_CONTENT
            );
        }
        return new ResponseEntity<>(
                participaciones,
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participacion> buscarPorId(
            @PathVariable Long id
    ) {
        try {
            Participacion participacion =
                    participacionService.obtenerPorId(id);
            return new ResponseEntity<>(
                    participacion,
                    HttpStatus.OK
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping
    public ResponseEntity<Participacion> guardarParticipacion(
            @Valid @RequestBody Participacion participacion
    ) {
        try {
            Participacion guardada =
                    participacionService
                            .guardarParticipacion(
                                    participacion
                            );
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
    public ResponseEntity<String> eliminarParticipacion(
            @PathVariable Long id
    ) {
        try {
            participacionService
                    .eliminarParticipacion(id);
            return new ResponseEntity<>(
                    "Participación eliminada",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Participación no encontrada",
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<ParticipacionDTO>>
    ranking() {
        List<ParticipacionDTO> ranking =
                participacionService
                        .generarRanking();
        return new ResponseEntity<>(
                ranking,
                HttpStatus.OK
        );
    }
}