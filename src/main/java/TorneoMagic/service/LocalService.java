package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.DTOs.LocalDTO;
import TorneoMagic.model.Comuna;
import TorneoMagic.model.Local;
import TorneoMagic.repository.ComunaRepository;
import TorneoMagic.repository.LocalRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class LocalService {
    
    private final LocalRepository localRepository;
    
    private final ComunaRepository comunaRepository;

    public LocalService(LocalRepository localRepository, ComunaRepository comunaRepository) {
        this.localRepository = localRepository;
        this.comunaRepository = comunaRepository;
    }

    public List<LocalDTO> listar() {
        return localRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<LocalDTO> obtenerPorId(Long id) {
        return localRepository.findById(id).map(this::toDTO);
    }

    public LocalDTO crear(LocalDTO localDTO) {
        Local local = fromDTO(localDTO);
        Local saved = localRepository.save(local);
        return toDTO(saved);
    }

    public LocalDTO actualizar(Long id, LocalDTO localDTO) {
        return localRepository.findById(id).map(localExistente -> {
            localExistente.setNombre(localDTO.nombre());
            localExistente.setDireccion(localDTO.direccion());
            localExistente.setCapacidad(localDTO.capacidad());
            Comuna comuna = comunaRepository.findById(localDTO.comunaId())
                    .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
            localExistente.setComuna(comuna);
            Local saved = localRepository.save(localExistente);
            return toDTO(saved);
        }).orElseThrow(() -> new RuntimeException("Local no encontrado"));
    }

    public void eliminar(Long id) {
        localRepository.deleteById(id);
    }

    // Transformer methods
    private LocalDTO toDTO(Local local) {
        return new LocalDTO(
                local.getId(),
                local.getNombre(),
                local.getDireccion(),
                local.getCapacidad(),
                local.getComuna().getId()
        );
    }

    private Local fromDTO(LocalDTO dto) {
        Comuna comuna = comunaRepository.findById(dto.comunaId())
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
        Local local = new Local();
        local.setNombre(dto.nombre());
        local.setDireccion(dto.direccion());
        local.setCapacidad(dto.capacidad());
        local.setComuna(comuna);
        return local;
    }
}
