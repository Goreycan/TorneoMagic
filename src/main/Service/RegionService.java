package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.model.Region;
import TorneoMagic.repository.RegionRepository;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> listar() {
        return regionRepository.findAll();
    }

    public Optional<Region> obtenerPorId(Long id) {
        return regionRepository.findById(id);
    }

    public Region crear(Region region) {
        return regionRepository.save(region);
    }

    public Region actualizar(Long id, Region region) {
        return regionRepository.findById(id).map(regionExistente -> {
            regionExistente.setNombre(region.getNombre());
            return regionRepository.save(regionExistente);
        }).orElseThrow(() -> new RuntimeException("Región no encontrada"));
    }

    public void eliminar(Long id) {
        regionRepository.deleteById(id);
    }
}
