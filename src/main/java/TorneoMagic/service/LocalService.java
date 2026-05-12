package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.dto.LocalDTO;
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

    // =====================================
    // LISTAR
    // =====================================

    public List<LocalDTO> listar() {
        return localRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    public Optional<LocalDTO> obtenerPorId(Long id) {
        return localRepository.findById(id)
                .map(this::toDTO);
    }

    // =====================================
    // CREAR
    // =====================================

    public LocalDTO crear(LocalDTO localDTO) {

        Local local = fromDTO(localDTO);

        Local saved = localRepository.save(local);

        return toDTO(saved);
    }

    // =====================================
    // ACTUALIZAR
    // =====================================

    public LocalDTO actualizar(Long id, LocalDTO localDTO) {

        return localRepository.findById(id)
                .map(localExistente -> {

                    localExistente.setNombre(localDTO.getNombre());
                    localExistente.setDireccion(localDTO.getDireccion());
                    localExistente.setCapacidad(localDTO.getCapacidad());

                    Comuna comuna = comunaRepository.findById(localDTO.getComunaId())
                            .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));

                    localExistente.setComuna(comuna);

                    Local saved = localRepository.save(localExistente);

                    return toDTO(saved);

                }).orElseThrow(() -> new RuntimeException("Local no encontrado"));
    }

    // =====================================
    // ELIMINAR
    // =====================================

    public void eliminar(Long id) {
        localRepository.deleteById(id);
    }

    // =====================================
    // CONVERTIR A DTO
    // =====================================

    private LocalDTO toDTO(Local local) {

        return LocalDTO.builder()
                .id(local.getId())
                .nombre(local.getNombre())
                .direccion(local.getDireccion())
                .capacidad(local.getCapacidad())
                .comunaId(local.getComuna().getId())
                .build();
    }

    // =====================================
    // CONVERTIR A ENTITY
    // =====================================

    private Local fromDTO(LocalDTO dto) {

        Comuna comuna = comunaRepository.findById(dto.getComunaId())
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));

        Local local = new Local();

        local.setNombre(dto.getNombre());
        local.setDireccion(dto.getDireccion());
        local.setCapacidad(dto.getCapacidad());
        local.setComuna(comuna);

        return local;
    }
}