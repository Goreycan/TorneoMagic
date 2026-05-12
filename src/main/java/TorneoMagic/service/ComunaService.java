package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.dto.ComunaDTO;
import TorneoMagic.model.Comuna;
import TorneoMagic.model.Region;
import TorneoMagic.repository.ComunaRepository;
import TorneoMagic.repository.RegionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    private final ComunaRepository comunaRepository;

    private final RegionRepository regionRepository;

    public ComunaService(
            ComunaRepository comunaRepository,
            RegionRepository regionRepository) {

        this.comunaRepository = comunaRepository;
        this.regionRepository = regionRepository;
    }

    // =====================================
    // LISTAR
    // =====================================

    public List<ComunaDTO> listar() {

        return comunaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    public Optional<ComunaDTO> obtenerPorId(Long id) {

        return comunaRepository.findById(id)
                .map(this::toDTO);
    }

    // =====================================
    // CREAR
    // =====================================

    public ComunaDTO crear(ComunaDTO comunaDTO) {

        Comuna comuna = fromDTO(comunaDTO);

        Comuna saved = comunaRepository.save(comuna);

        return toDTO(saved);
    }

    // =====================================
    // ACTUALIZAR
    // =====================================

    public ComunaDTO actualizar(Long id, ComunaDTO comunaDTO) {

        return comunaRepository.findById(id)
                .map(comunaExistente -> {

                    comunaExistente.setNombre(comunaDTO.getNombre());

                    Region region = regionRepository.findById(comunaDTO.getRegionId())
                            .orElseThrow(() -> new RuntimeException("Región no encontrada"));

                    comunaExistente.setRegion(region);

                    Comuna saved = comunaRepository.save(comunaExistente);

                    return toDTO(saved);

                }).orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
    }

    // =====================================
    // ELIMINAR
    // =====================================

    public void eliminar(Long id) {
        comunaRepository.deleteById(id);
    }

    // =====================================
    // CONVERTIR A DTO
    // =====================================

    private ComunaDTO toDTO(Comuna comuna) {

        return ComunaDTO.builder()
                .id(comuna.getId())
                .nombre(comuna.getNombre())
                .regionId(comuna.getRegion().getId())
                .build();
    }

    // =====================================
    // CONVERTIR A ENTITY
    // =====================================

    private Comuna fromDTO(ComunaDTO dto) {

        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> new RuntimeException("Región no encontrada"));

        Comuna comuna = new Comuna();

        comuna.setNombre(dto.getNombre());
        comuna.setRegion(region);

        return comuna;
    }
}