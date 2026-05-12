package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.dto.RegionDTO;
import TorneoMagic.model.Region;
import TorneoMagic.repository.RegionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    // =====================================
    // LISTAR
    // =====================================

    public List<RegionDTO> listar() {

        return regionRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    public Optional<RegionDTO> obtenerPorId(Long id) {

        return regionRepository.findById(id)
                .map(this::toDTO);
    }

    // =====================================
    // CREAR
    // =====================================

    public RegionDTO crear(RegionDTO regionDTO) {

        Region region = fromDTO(regionDTO);

        Region saved = regionRepository.save(region);

        return toDTO(saved);
    }

    // =====================================
    // ACTUALIZAR
    // =====================================

    public RegionDTO actualizar(Long id, RegionDTO regionDTO) {

        return regionRepository.findById(id)
                .map(regionExistente -> {

                    regionExistente.setNombre(regionDTO.getNombre());

                    Region saved = regionRepository.save(regionExistente);

                    return toDTO(saved);

                }).orElseThrow(() -> new RuntimeException("Región no encontrada"));
    }

    // =====================================
    // ELIMINAR
    // =====================================

    public void eliminar(Long id) {
        regionRepository.deleteById(id);
    }

    // =====================================
    // CONVERTIR A DTO
    // =====================================

    private RegionDTO toDTO(Region region) {

        return RegionDTO.builder()
                .id(region.getId())
                .nombre(region.getNombre())
                .build();
    }

    // =====================================
    // CONVERTIR A ENTITY
    // =====================================

    private Region fromDTO(RegionDTO dto) {

        Region region = new Region();

        region.setNombre(dto.getNombre());

        return region;
    }
}