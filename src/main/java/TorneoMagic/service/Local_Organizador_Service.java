package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.dto.Local_Organizador_DTO;
import TorneoMagic.model.Local;
import TorneoMagic.model.Local_Organizador;
import TorneoMagic.model.Organizador;
import TorneoMagic.repository.LocalRepository;
import TorneoMagic.repository.Local_Organizador_Repository;
import TorneoMagic.repository.OrganizadorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class Local_Organizador_Service {

    private final Local_Organizador_Repository local_Organizador_Repository;

    private final LocalRepository localRepository;

    private final OrganizadorRepository organizadorRepository;

    public Local_Organizador_Service(
            Local_Organizador_Repository local_Organizador_Repository,
            LocalRepository localRepository,
            OrganizadorRepository organizadorRepository) {

        this.local_Organizador_Repository = local_Organizador_Repository;
        this.localRepository = localRepository;
        this.organizadorRepository = organizadorRepository;
    }

    // =====================================
    // LISTAR
    // =====================================

    public List<Local_Organizador_DTO> listar() {

        return local_Organizador_Repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // =====================================
    // OBTENER POR ID
    // =====================================

    public Optional<Local_Organizador_DTO> obtenerPorId(Long id) {

        return local_Organizador_Repository.findById(id)
                .map(this::toDTO);
    }

    // =====================================
    // CREAR
    // =====================================

    public Local_Organizador_DTO crear(Local_Organizador_DTO dto) {

        Local_Organizador entidad = fromDTO(dto);

        Local_Organizador saved =
                local_Organizador_Repository.save(entidad);

        return toDTO(saved);
    }

    // =====================================
    // ACTUALIZAR
    // =====================================

    public Local_Organizador_DTO actualizar(
            Long id,
            Local_Organizador_DTO dto) {

        return local_Organizador_Repository.findById(id)
                .map(existente -> {

                    Local local = localRepository.findById(dto.getLocalId())
                            .orElseThrow(() -> new RuntimeException("Local no encontrado"));

                    Organizador organizador =
                            organizadorRepository.findById(dto.getOrganizadorId())
                                    .orElseThrow(() -> new RuntimeException("Organizador no encontrado"));

                    existente.setLocal(local);
                    existente.setOrganizador(organizador);
                    existente.setCargo(dto.getCargo());
                    existente.setFechaAsignacion(dto.getFechaAsignacion());

                    Local_Organizador saved =
                            local_Organizador_Repository.save(existente);

                    return toDTO(saved);

                }).orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
    }

    // =====================================
    // ELIMINAR
    // =====================================

    public void eliminar(Long id) {
        local_Organizador_Repository.deleteById(id);
    }

    // =====================================
    // CONVERTIR A DTO
    // =====================================

    private Local_Organizador_DTO toDTO(Local_Organizador entidad) {

        return Local_Organizador_DTO.builder()
                .id(entidad.getId())
                .localId(entidad.getLocal().getId())
                .organizadorId(entidad.getOrganizador().getId())
                .cargo(entidad.getCargo())
                .fechaAsignacion(entidad.getFechaAsignacion())
                .build();
    }

    // =====================================
    // CONVERTIR A ENTITY
    // =====================================

    private Local_Organizador fromDTO(Local_Organizador_DTO dto) {

        Local local = localRepository.findById(dto.getLocalId())
                .orElseThrow(() -> new RuntimeException("Local no encontrado"));

        Organizador organizador =
                organizadorRepository.findById(dto.getOrganizadorId())
                        .orElseThrow(() -> new RuntimeException("Organizador no encontrado"));

        Local_Organizador entidad = new Local_Organizador();

        entidad.setLocal(local);
        entidad.setOrganizador(organizador);
        entidad.setCargo(dto.getCargo());
        entidad.setFechaAsignacion(dto.getFechaAsignacion());

        return entidad;
    }
}