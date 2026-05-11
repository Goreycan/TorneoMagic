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

import TorneoMagic.model.Local;
import TorneoMagic.service.LocalService;

@RestController
@RequestMapping("/api/locales")
public class LocalController {
    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping
    public ResponseEntity<List<Local>> listar() {
        return ResponseEntity.ok(localService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> obtenerPorId(@PathVariable Long id) {
        Optional<Local> local = localService.obtenerPorId(id);
        return local.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Local> crear(@RequestBody Local local) {
        return ResponseEntity.status(HttpStatus.CREATED).body(localService.crear(local));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> actualizar(@PathVariable Long id, @RequestBody Local local) {
        return ResponseEntity.ok(localService.actualizar(id, local));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        localService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
