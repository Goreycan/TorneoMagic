package TorneoMagic.controller;

import TorneoMagic.dto.PartidaDTO;
import TorneoMagic.model.Partida;
import TorneoMagic.service.PartidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/partidas")
public class PartidaController {

    @Autowired
    private PartidaService partidaService;

    @GetMapping
    public ResponseEntity<List<PartidaDTO>> listarTodas() {
        List<PartidaDTO> partidas = partidaService.obtenerTodas();
        if (partidas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(partidas,HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partida> buscarPorId(@PathVariable Long id) {
        try {
            Partida partida = partidaService.obtenerPorId(id);
            return new ResponseEntity<>(partida,HttpStatus.OK);
        } 
        catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Partida> guardarPartida(@Valid @RequestBody Partida partida) {
        try {
            Partida guardada = partidaService.guardarPartida(partida);
            return new ResponseEntity<>(guardada,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPartida(@PathVariable Long id) {
        try {
            partidaService.eliminarPartida(id);
            return new ResponseEntity<>("Partida eliminada",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Partida no encontrada",HttpStatus.NOT_FOUND);
        }
    }
}