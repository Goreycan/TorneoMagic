package TorneoMagic.service;

import TorneoMagic.model.Partida;
import TorneoMagic.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidaService {

    private final PartidaRepository partidaRepository;

    public PartidaService(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    public List<Partida> listarPartidas() {
        return partidaRepository.findAll();
    }

    public Partida guardarPartida(Partida partida) {
        return partidaRepository.save(partida);
    }
}