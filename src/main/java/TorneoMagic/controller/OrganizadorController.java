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

import TorneoMagic.DTOs.OrganizadorDTO;
import TorneoMagic.service.OrganizadorService;

@RestController
@RequestMapping("/api/organizadores")
public class OrganizadorController {
    private final OrganizadorService organizadorService;

    public OrganizadorController(OrganizadorService organizadorService) {
        this.organizadorService = organizadorService;
    }

    @GetMapping
    public ResponseEntity<List<OrganizadorDTO>> listar() {
        return ResponseEntity.ok(organizadorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> obtenerPorId(@PathVariable Long id) {
        Optional<OrganizadorDTO> organizador = organizadorService.obtenerPorId(id);
        return organizador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrganizadorDTO> crear(@RequestBody OrganizadorDTO organizadorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizadorService.crear(organizadorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> actualizar(@PathVariable Long id, @RequestBody OrganizadorDTO organizadorDTO) {
        return ResponseEntity.ok(organizadorService.actualizar(id, organizadorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        organizadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
