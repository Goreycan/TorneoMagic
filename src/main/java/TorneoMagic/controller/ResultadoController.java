package TorneoMagic.controller;

import TorneoMagic.model.Participacion;
import TorneoMagic.model.Resultado;
import TorneoMagic.service.ParticipacionService;
import TorneoMagic.service.ResultadoService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/resultados")
public class ResultadoController {

    private final ResultadoService resultadoService;
    private final ParticipacionService participacionService;

    public ResultadoController(
            ResultadoService resultadoService,
            ParticipacionService participacionService
    ) {
        this.resultadoService = resultadoService;
        this.participacionService = participacionService;
    }

    // =====================================
    // CRUD
    // =====================================

    @GetMapping
    public List<Resultado> listarResultados() {

        return resultadoService.listarResultados();
    }

    @PostMapping
    public Resultado guardarResultado(
            @Valid @RequestBody Resultado resultado
    ) {

        return resultadoService.guardarResultado(resultado);
    }

    // =====================================
    // REGISTRAR RESULTADO
    // =====================================

    @PostMapping("/registrar")
    public Resultado registrarResultado(
            @Valid @RequestBody Resultado resultado
    ) {

        return resultadoService
                .registrarResultado(resultado);
    }

    // =====================================
    // RANKING
    // =====================================

    @GetMapping("/ranking")
    public List<Participacion> ranking() {

        return participacionService
                .generarRanking();
    }
}