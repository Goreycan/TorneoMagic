package TorneoMagic.service;

import TorneoMagic.model.Resultado;
import TorneoMagic.repository.ResultadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultadoService {

    private final ResultadoRepository resultadoRepository;

    public ResultadoService(ResultadoRepository resultadoRepository) {
        this.resultadoRepository = resultadoRepository;
    }

    public List<Resultado> listarResultados() {
        return resultadoRepository.findAll();
    }

    public Resultado guardarResultado(Resultado resultado) {
        return resultadoRepository.save(resultado);
    }

    public Resultado obtenerPorId(Long id) {
        return resultadoRepository.findById(id).orElse(null);
    }

    public void eliminarResultado(Long id) {
        resultadoRepository.deleteById(id);
    }
}