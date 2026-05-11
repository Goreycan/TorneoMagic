package TorneoMagic.controller;

import TorneoMagic.DTO.ResultadoDTO;
import TorneoMagic.model.Resultado;
import TorneoMagic.service.ResultadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resultados")
public class ResultadoController {

    @Autowired
    private ResultadoService resultadoService;

    @GetMapping
    public ResponseEntity<List<ResultadoDTO>> listarTodos() {
        List<ResultadoDTO> resultados =
                resultadoService.obtenerTodos();
        if (resultados.isEmpty()) {
            return new ResponseEntity<>(
                    HttpStatus.NO_CONTENT
            );
        }
        return new ResponseEntity<>(
                resultados,
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultado> buscarPorId(
            @PathVariable Long id
    ) {
        try {
            Resultado resultado =
                    resultadoService.obtenerPorId(id);
            return new ResponseEntity<>(
                    resultado,
                    HttpStatus.OK
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }
    @PostMapping
    public ResponseEntity<Resultado> guardarResultado(
            @Valid @RequestBody Resultado resultado
    ) {
        try {
            Resultado guardado =
                    resultadoService
                            .guardarResultado(resultado);
            return new ResponseEntity<>(
                    guardado,
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<Resultado> registrarResultado(
            @Valid @RequestBody Resultado resultado
    ) {
        try {
            Resultado registrado =
                    resultadoService
                            .registrarResultado(resultado);
            return new ResponseEntity<>(
                    registrado,
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarResultado(
            @PathVariable Long id
    ) {
        try {
            resultadoService.eliminarResultado(id);
            return new ResponseEntity<>(
                    "Resultado eliminado",
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    "Resultado no encontrado",
                    HttpStatus.NOT_FOUND
            );
        }
    }
}