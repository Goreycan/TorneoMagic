package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.dto.OrganizadorDTO;
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

    // =====================================
    // LISTAR
    // =====================================

    public List<OrganizadorDTO> listar() {

        return organizadorRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    public Optional<OrganizadorDTO> obtenerPorId(Long id) {

        return organizadorRepository.findById(id)
                .map(this::toDTO);
    }

    // =====================================
    // CREAR
    // =====================================

    public OrganizadorDTO crear(OrganizadorDTO organizadorDTO) {

        Organizador organizador = fromDTO(organizadorDTO);

        Organizador saved = organizadorRepository.save(organizador);

        return toDTO(saved);
    }

    // =====================================
    // ACTUALIZAR
    // =====================================

    public OrganizadorDTO actualizar(Long id, OrganizadorDTO organizadorDTO) {

        return organizadorRepository.findById(id)
                .map(organizadorExistente -> {

                    organizadorExistente.setNombre(organizadorDTO.getNombre());
                    organizadorExistente.setApellido(organizadorDTO.getApellido());
                    organizadorExistente.setEmail(organizadorDTO.getEmail());
                    organizadorExistente.setTelefono(organizadorDTO.getTelefono());

                    Organizador saved = organizadorRepository.save(organizadorExistente);

                    return toDTO(saved);

                }).orElseThrow(() -> new RuntimeException("Organizador no encontrado"));
    }

    // =====================================
    // ELIMINAR
    // =====================================

    public void eliminar(Long id) {
        organizadorRepository.deleteById(id);
    }

    // =====================================
    // CONVERTIR A DTO
    // =====================================

    private OrganizadorDTO toDTO(Organizador organizador) {

        return OrganizadorDTO.builder()
                .id(organizador.getId())
                .nombre(organizador.getNombre())
                .apellido(organizador.getApellido())
                .email(organizador.getEmail())
                .telefono(organizador.getTelefono())
                .build();
    }

    // =====================================
    // CONVERTIR A ENTITY
    // =====================================

    private Organizador fromDTO(OrganizadorDTO dto) {

        Organizador organizador = new Organizador();

        organizador.setNombre(dto.getNombre());
        organizador.setApellido(dto.getApellido());
        organizador.setEmail(dto.getEmail());
        organizador.setTelefono(dto.getTelefono());

        return organizador;
    }
}