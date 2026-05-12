package TorneoMagic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import TorneoMagic.DTOs.LocalDTO;
import TorneoMagic.service.LocalService;

@RestController
@RequestMapping("/api/locales")
public class LocalController {
    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping
    public ResponseEntity<List<LocalDTO>> listar() {
        return ResponseEntity.ok(localService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> obtenerPorId(@PathVariable Long id) {
        Optional<LocalDTO> local = localService.obtenerPorId(id);
        return local.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LocalDTO> crear(@Valid @RequestBody LocalDTO localDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(localService.crear(localDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalDTO> actualizar( @PathVariable Long id, @Valid @RequestBody LocalDTO localDTO) {
        return ResponseEntity.ok(localService.actualizar(id, localDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        localService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
