package TorneoMagic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import TorneoMagic.dto.OrganizadorDTO;
import TorneoMagic.service.OrganizadorService;

@RestController
@RequestMapping("/api/organizadores")
public class OrganizadorController {

    private final OrganizadorService organizadorService;

    public OrganizadorController(OrganizadorService organizadorService) {
        this.organizadorService = organizadorService;
    }

    // =====================================
    // LISTAR
    // =====================================

    @GetMapping
    public ResponseEntity<List<OrganizadorDTO>> listar() {

        return ResponseEntity.ok(organizadorService.listar());
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    @GetMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> obtenerPorId(@PathVariable Long id) {

        Optional<OrganizadorDTO> organizador =
                organizadorService.obtenerPorId(id);

        return organizador
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // =====================================
    // CREAR
    // =====================================

    @PostMapping
    public ResponseEntity<OrganizadorDTO> crear(
            @Valid @RequestBody OrganizadorDTO organizadorDTO) {

        OrganizadorDTO creado =
                organizadorService.crear(organizadorDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // =====================================
    // ACTUALIZAR
    // =====================================

    @PutMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody OrganizadorDTO organizadorDTO) {

        OrganizadorDTO actualizado =
                organizadorService.actualizar(id, organizadorDTO);

        return ResponseEntity.ok(actualizado);
    }

    // =====================================
    // ELIMINAR
    // =====================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        organizadorService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}