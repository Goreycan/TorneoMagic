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

    public List<RegionDTO> listar() {
        return regionRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<RegionDTO> obtenerPorId(Long id) {
        return regionRepository.findById(id).map(this::toDTO);
    }

    public RegionDTO crear(RegionDTO regionDTO) {
        Region region = fromDTO(regionDTO);
        Region saved = regionRepository.save(region);
        return toDTO(saved);
    }

    public RegionDTO actualizar(Long id, RegionDTO regionDTO) {
        return regionRepository.findById(id).map(regionExistente -> {
            regionExistente.setNombre(regionDTO.nombre());
            Region saved = regionRepository.save(regionExistente);
            return toDTO(saved);
        }).orElseThrow(() -> new RuntimeException("Región no encontrada"));
    }

    public void eliminar(Long id) {
        regionRepository.deleteById(id);
    }

    // Transformer methods
    private RegionDTO toDTO(Region region) {
        return new RegionDTO(
                region.getId(),
                region.getNombre()
        );
    }

    private Region fromDTO(RegionDTO dto) {
        Region region = new Region();
        region.setNombre(dto.nombre());
        return region;
    }
}
