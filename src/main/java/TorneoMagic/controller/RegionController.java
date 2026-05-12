package TorneoMagic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import TorneoMagic.dto.RegionDTO;
import TorneoMagic.service.RegionService;

@RestController
@RequestMapping("/api/regiones")
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    // =====================================
    // LISTAR
    // =====================================

    @GetMapping
    public ResponseEntity<List<RegionDTO>> listar() {

        return ResponseEntity.ok(regionService.listar());
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> obtenerPorId(@PathVariable Long id) {

        Optional<RegionDTO> region =
                regionService.obtenerPorId(id);

        return region
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // =====================================
    // CREAR
    // =====================================

    @PostMapping
    public ResponseEntity<RegionDTO> crear(
            @Valid @RequestBody RegionDTO regionDTO) {

        RegionDTO creada =
                regionService.crear(regionDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creada);
    }

    // =====================================
    // ACTUALIZAR
    // =====================================

    @PutMapping("/{id}")
    public ResponseEntity<RegionDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody RegionDTO regionDTO) {

        RegionDTO actualizada =
                regionService.actualizar(id, regionDTO);

        return ResponseEntity.ok(actualizada);
    }

    // =====================================
    // ELIMINAR
    // =====================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        regionService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}