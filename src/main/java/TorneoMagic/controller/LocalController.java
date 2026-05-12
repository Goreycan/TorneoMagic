package TorneoMagic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import TorneoMagic.dto.LocalDTO;
import TorneoMagic.service.LocalService;

@RestController
@RequestMapping("/api/locales")
public class LocalController {

    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    // =====================================
    // LISTAR
    // =====================================

    @GetMapping
    public ResponseEntity<List<LocalDTO>> listar() {

        return ResponseEntity.ok(localService.listar());
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> obtenerPorId(@PathVariable Long id) {

        Optional<LocalDTO> local =
                localService.obtenerPorId(id);

        return local
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // =====================================
    // CREAR
    // =====================================

    @PostMapping
    public ResponseEntity<LocalDTO> crear(
            @Valid @RequestBody LocalDTO localDTO) {

        LocalDTO creado =
                localService.crear(localDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // =====================================
    // ACTUALIZAR
    // =====================================

    @PutMapping("/{id}")
    public ResponseEntity<LocalDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody LocalDTO localDTO) {

        LocalDTO actualizado =
                localService.actualizar(id, localDTO);

        return ResponseEntity.ok(actualizado);
    }

    // =====================================
    // ELIMINAR
    // =====================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        localService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}