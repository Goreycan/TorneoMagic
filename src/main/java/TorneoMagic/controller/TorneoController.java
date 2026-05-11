package TorneoMagic.controller;

import TorneoMagic.DTO.TorneoDTO;
import TorneoMagic.model.Torneo;
import TorneoMagic.service.TorneoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/torneos")
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    @GetMapping
    public ResponseEntity<List<TorneoDTO>> listarTodos() {
        List<TorneoDTO> torneos = torneoService.obtenerTodos();
        if (torneos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(torneos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Torneo> buscarPorId(@PathVariable Long id) {
        try {
            Torneo torneo =torneoService.obtenerPorId(id);
            return new ResponseEntity<>(torneo,HttpStatus.OK);
        } 
        catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Torneo> guardarTorneo(@Valid @RequestBody Torneo torneo) {
        try {
            Torneo guardado =torneoService.guardarTorneo(torneo);
            return new ResponseEntity<>(guardado,HttpStatus.CREATED);
        } 
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTorneo(@PathVariable Long id) {
        try {
            torneoService.eliminarTorneo(id);
            return new ResponseEntity<>("Torneo eliminado",HttpStatus.OK);
        } 
        catch (Exception e) {
            return new ResponseEntity<>("Torneo no encontrado",HttpStatus.NOT_FOUND);
        }
    }
}