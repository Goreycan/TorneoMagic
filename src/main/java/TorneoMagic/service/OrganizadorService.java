package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.DTOs.OrganizadorDTO;
import TorneoMagic.model.Organizador;
import TorneoMagic.repository.OrganizadorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrganizadorService {
    
    private final OrganizadorRepository organizadorRepository;

    public OrganizadorService(OrganizadorRepository organizadorRepository) {
        this.organizadorRepository = organizadorRepository;
    }

    public List<OrganizadorDTO> listar() {
        return organizadorRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<OrganizadorDTO> obtenerPorId(Long id) {
        return organizadorRepository.findById(id).map(this::toDTO);
    }

    public OrganizadorDTO crear(OrganizadorDTO organizadorDTO) {
        Organizador organizador = fromDTO(organizadorDTO);
        Organizador saved = organizadorRepository.save(organizador);
        return toDTO(saved);
    }

    public OrganizadorDTO actualizar(Long id, OrganizadorDTO organizadorDTO) {
        return organizadorRepository.findById(id).map(organizadorExistente -> {
            organizadorExistente.setNombre(organizadorDTO.nombre());
            organizadorExistente.setApellido(organizadorDTO.apellido());
            organizadorExistente.setEmail(organizadorDTO.email());
            organizadorExistente.setTelefono(organizadorDTO.telefono());
            Organizador saved = organizadorRepository.save(organizadorExistente);
            return toDTO(saved);
        }).orElseThrow(() -> new RuntimeException("Organizador no encontrado"));
    }

    public void eliminar(Long id) {
        organizadorRepository.deleteById(id);
    }

    // Transformer methods
    private OrganizadorDTO toDTO(Organizador organizador) {
        return new OrganizadorDTO(
                organizador.getId(),
                organizador.getNombre(),
                organizador.getApellido(),
                organizador.getEmail(),
                organizador.getTelefono()
        );
    }

    private Organizador fromDTO(OrganizadorDTO dto) {
        Organizador organizador = new Organizador();
        organizador.setNombre(dto.nombre());
        organizador.setApellido(dto.apellido());
        organizador.setEmail(dto.email());
        organizador.setTelefono(dto.telefono());
        return organizador;
    }
}
