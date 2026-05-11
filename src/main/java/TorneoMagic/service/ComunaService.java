package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.DTOs.ComunaDTO;
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

    public ComunaService(ComunaRepository comunaRepository, RegionRepository regionRepository) {
        this.comunaRepository = comunaRepository;
        this.regionRepository = regionRepository;
    }

    public List<ComunaDTO> listar() {
        return comunaRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<ComunaDTO> obtenerPorId(Long id) {
        return comunaRepository.findById(id).map(this::toDTO);
    }

    public ComunaDTO crear(ComunaDTO comunaDTO) {
        Comuna comuna = fromDTO(comunaDTO);
        Comuna saved = comunaRepository.save(comuna);
        return toDTO(saved);
    }

    public ComunaDTO actualizar(Long id, ComunaDTO comunaDTO) {
        return comunaRepository.findById(id).map(comunaExistente -> {
            comunaExistente.setNombre(comunaDTO.nombre());
            Region region = regionRepository.findById(comunaDTO.regionId())
                    .orElseThrow(() -> new RuntimeException("Region no encontrada"));
            comunaExistente.setRegion(region);
            Comuna saved = comunaRepository.save(comunaExistente);
            return toDTO(saved);
        }).orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
    }

    public void eliminar(Long id) {
        comunaRepository.deleteById(id);
    }

    // Transformer methods
    private ComunaDTO toDTO(Comuna comuna) {
        return new ComunaDTO(
                comuna.getId(),
                comuna.getNombre(),
                comuna.getRegion().getId()
        );
    }

    private Comuna fromDTO(ComunaDTO dto) {
        Region region = regionRepository.findById(dto.regionId())
                .orElseThrow(() -> new RuntimeException("Region no encontrada"));
        Comuna comuna = new Comuna();
        comuna.setNombre(dto.nombre());
        comuna.setRegion(region);
        return comuna;
    }
}
