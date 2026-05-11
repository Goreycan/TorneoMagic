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

import TorneoMagic.DTOs.ComunaDTO;
import TorneoMagic.service.ComunaService;

@RestController
@RequestMapping("/api/comunas")
public class ComunaController {
    private final ComunaService comunaService;

    public ComunaController(ComunaService comunaService) {
        this.comunaService = comunaService;
    }

    @GetMapping
    public ResponseEntity<List<ComunaDTO>> listar() {
        return ResponseEntity.ok(comunaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunaDTO> obtenerPorId(@PathVariable Long id) {
        Optional<ComunaDTO> comuna = comunaService.obtenerPorId(id);
        return comuna.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ComunaDTO> crear(@RequestBody ComunaDTO comunaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comunaService.crear(comunaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComunaDTO> actualizar(@PathVariable Long id, @RequestBody ComunaDTO comunaDTO) {
        return ResponseEntity.ok(comunaService.actualizar(id, comunaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        comunaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
