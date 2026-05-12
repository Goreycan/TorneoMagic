package TorneoMagic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import TorneoMagic.dto.Local_Organizador_DTO;
import TorneoMagic.service.Local_Organizador_Service;

@RestController
@RequestMapping("/api/local-organizadores")
public class Local_Organizador_Controller {

    private final Local_Organizador_Service local_Organizador_Service;

    public Local_Organizador_Controller(Local_Organizador_Service local_Organizador_Service) {
        this.local_Organizador_Service = local_Organizador_Service;
    }

    // =====================================
    // LISTAR
    // =====================================

    @GetMapping
    public ResponseEntity<List<Local_Organizador_DTO>> listar() {

        return ResponseEntity.ok(local_Organizador_Service.listar());
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    @GetMapping("/{id}")
    public ResponseEntity<Local_Organizador_DTO> obtenerPorId(@PathVariable Long id) {

        Optional<Local_Organizador_DTO> localOrganizador =
                local_Organizador_Service.obtenerPorId(id);

        return localOrganizador
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // =====================================
    // CREAR
    // =====================================

    @PostMapping
    public ResponseEntity<Local_Organizador_DTO> crear(
            @Valid @RequestBody Local_Organizador_DTO local_Organizador_DTO) {

        Local_Organizador_DTO creado =
                local_Organizador_Service.crear(local_Organizador_DTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(creado);
    }

    // =====================================
    // ACTUALIZAR
    // =====================================

    @PutMapping("/{id}")
    public ResponseEntity<Local_Organizador_DTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Local_Organizador_DTO local_Organizador_DTO) {

        Local_Organizador_DTO actualizado =
                local_Organizador_Service.actualizar(id, local_Organizador_DTO);

        return ResponseEntity.ok(actualizado);
    }

    // =====================================
    // ELIMINAR
    // =====================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        local_Organizador_Service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}