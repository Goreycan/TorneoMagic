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

import TorneoMagic.DTOs.Local_Organizador_DTO;
import TorneoMagic.service.Local_Organizador_Service;

@RestController
@RequestMapping("/api/local-organizadores")
public class Local_Organizador_Controller {
    private final Local_Organizador_Service local_Organizador_Service;

    public Local_Organizador_Controller(Local_Organizador_Service local_Organizador_Service) {
        this.local_Organizador_Service = local_Organizador_Service;
    }

    @GetMapping
    public ResponseEntity<List<Local_Organizador_DTO>> listar() {
        return ResponseEntity.ok(local_Organizador_Service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local_Organizador_DTO> obtenerPorId(@PathVariable Long id) {
        Optional<Local_Organizador_DTO> localOrganizador = local_Organizador_Service.obtenerPorId(id);
        return localOrganizador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Local_Organizador_DTO> crear(@Valid @RequestBody Local_Organizador_DTO local_Organizador_DTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(local_Organizador_Service.crear(local_Organizador_DTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local_Organizador_DTO> actualizar(@Valid @PathVariable Long id, @RequestBody Local_Organizador_DTO local_Organizador_DTO) {
        return ResponseEntity.ok(local_Organizador_Service.actualizar(id, local_Organizador_DTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        local_Organizador_Service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
