package TorneoMagic.service;

import TorneoMagic.DTO.ResultadoDTO;
import TorneoMagic.model.Participacion;
import TorneoMagic.model.Partida;
import TorneoMagic.model.Resultado;
import TorneoMagic.repository.PartidaRepository;
import TorneoMagic.repository.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ResultadoService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Autowired
    private ParticipacionService participacionService;

    @Autowired
    private PartidaRepository partidaRepository;

    // =====================================
    // OBTENER TODOS
    // =====================================

    public List<ResultadoDTO> obtenerTodos() {
        return resultadoRepository.findAll().stream().map(this::convertirADTO).toList();
    }

    // =====================================
    // GUARDAR
    // =====================================

    public Resultado guardarResultado(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    public Resultado obtenerPorId(Long id) {
        return resultadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Resultado no encontrado"));
    }

    // =====================================
    // ELIMINAR
    // =====================================

    public void eliminarResultado(Long id) {
        resultadoRepository.deleteById(id);
    }

    // =====================================
    // REGISTRAR RESULTADO
    // =====================================

    public Resultado registrarResultado(Resultado resultado) {
        Partida partida = resultado.getPartida();
        if (partida == null) {
            throw new RuntimeException("La partida es obligatoria");
        }

        if (resultado.getGanador() == null) {
            throw new RuntimeException("Debe existir un ganador");
        }

        Resultado resultadoGuardado = resultadoRepository.save(resultado);
        partida.setEstado("FINALIZADA");
        partidaRepository.save(partida);
        List<Participacion> participaciones = participacionService.listarParticipaciones();
        for (Participacion participacion : participaciones) {
            if (participacion.getJugador().getId().equals(resultado.getGanador().getId())) 
            {
                participacionService.sumarPuntos(participacion,3);
            }
        }
        return resultadoGuardado;
    }

    // =====================================
    // CONVERTIR DTO
    // =====================================

    private ResultadoDTO convertirADTO(Resultado resultado) {
        ResultadoDTO dto = new ResultadoDTO();
        dto.setId(resultado.getId());
        if (resultado.getPartida() != null) {
            dto.setMesa(resultado.getPartida().getMesa());
        }

        if (resultado.getGanador() != null) {
            dto.setNombreGanador(resultado.getGanador().getNombre());
        }
        dto.setPuntajeJugador1(resultado.getPuntajeJugador1()
        );
        dto.setPuntajeJugador2(resultado.getPuntajeJugador2()
        );
        dto.setPuntajeJugador3(resultado.getPuntajeJugador3()
        );
        dto.setPuntajeJugador4(resultado.getPuntajeJugador4()
        );
        dto.setPuntajeJugador5(resultado.getPuntajeJugador5()
        );
        dto.setObservaciones(resultado.getObservaciones()
        );
        return dto;
    }
}