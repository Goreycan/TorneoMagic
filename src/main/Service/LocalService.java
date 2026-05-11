package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.model.Local;
import TorneoMagic.repository.LocalRepository;

@Service
public class LocalService {
    private final LocalRepository localRepository;

    public LocalService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    public List<Local> listar() {
        return localRepository.findAll();
    }

    public Optional<Local> obtenerPorId(Long id) {
        return localRepository.findById(id);
    }

    public Local crear(Local local) {
        return localRepository.save(local);
    }

    public Local actualizar(Long id, Local local) {
        return localRepository.findById(id).map(localExistente -> {
            localExistente.setNombre(local.getNombre());
            localExistente.setDireccion(local.getDireccion());
            localExistente.setCapacidad(local.getCapacidad());
            localExistente.setComuna(local.getComuna());
            return localRepository.save(localExistente);
        }).orElseThrow(() -> new RuntimeException("Local no encontrado"));
    }

    public void eliminar(Long id) {
        localRepository.deleteById(id);
    }
}
