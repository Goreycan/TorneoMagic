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

import TorneoMagic.model.Organizador;
import TorneoMagic.service.OrganizadorService;

@RestController
@RequestMapping("/api/organizadores")
public class OrganizadorController {
    private final OrganizadorService organizadorService;

    public OrganizadorController(OrganizadorService organizadorService) {
        this.organizadorService = organizadorService;
    }

    @GetMapping
    public ResponseEntity<List<Organizador>> listar() {
        return ResponseEntity.ok(organizadorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizador> obtenerPorId(@PathVariable Long id) {
        Optional<Organizador> organizador = organizadorService.obtenerPorId(id);
        return organizador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Organizador> crear(@RequestBody Organizador organizador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizadorService.crear(organizador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizador> actualizar(@PathVariable Long id, @RequestBody Organizador organizador) {
        return ResponseEntity.ok(organizadorService.actualizar(id, organizador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        organizadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
