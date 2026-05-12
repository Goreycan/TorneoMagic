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
import TorneoMagic.DTOs.RegionDTO;
import TorneoMagic.service.RegionService;

@RestController
@RequestMapping("/api/regiones")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<List<RegionDTO>> listar() {
        return ResponseEntity.ok(regionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> obtenerPorId(@PathVariable Long id) {
        Optional<RegionDTO> region = regionService.obtenerPorId(id);
        return region.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RegionDTO> crear(@Valid @RequestBody RegionDTO regionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(regionService.crear(regionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionDTO> actualizar(@PathVariable Long id,@Valid @RequestBody RegionDTO regionDTO) {
        return ResponseEntity.ok(regionService.actualizar(id, regionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        regionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
