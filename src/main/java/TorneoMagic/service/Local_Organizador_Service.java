package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.DTOs.Local_Organizador_DTO;
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

    public Local_Organizador_Service(Local_Organizador_Repository local_Organizador_Repository, LocalRepository localRepository, OrganizadorRepository organizadorRepository) {
        this.local_Organizador_Repository = local_Organizador_Repository;
        this.localRepository = localRepository;
        this.organizadorRepository = organizadorRepository;
    }

    public List<Local_Organizador_DTO> listar() {
        return local_Organizador_Repository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<Local_Organizador_DTO> obtenerPorId(Long id) {
        return local_Organizador_Repository.findById(id).map(this::toDTO);
    }

    public Local_Organizador_DTO crear(Local_Organizador_DTO local_Organizador_DTO) {
        Local_Organizador local_Organizador = fromDTO(local_Organizador_DTO);
        Local_Organizador saved = local_Organizador_Repository.save(local_Organizador);
        return toDTO(saved);
    }

    public Local_Organizador_DTO actualizar(Long id, Local_Organizador_DTO local_Organizador_DTO) {
        return local_Organizador_Repository.findById(id).map(localOrganizadorExistente -> {
            Local local = localRepository.findById(local_Organizador_DTO.localId())
                    .orElseThrow(() -> new RuntimeException("Local no encontrado"));
            Organizador organizador = organizadorRepository.findById(local_Organizador_DTO.organizadorId())
                    .orElseThrow(() -> new RuntimeException("Organizador no encontrado"));
            localOrganizadorExistente.setLocal(local);
            localOrganizadorExistente.setOrganizador(organizador);
            localOrganizadorExistente.setCargo(local_Organizador_DTO.cargo());
            localOrganizadorExistente.setFechaAsignacion(local_Organizador_DTO.fechaAsignacion());
            Local_Organizador saved = local_Organizador_Repository.save(localOrganizadorExistente);
            return toDTO(saved);
        }).orElseThrow(() -> new RuntimeException("Local_Organizador no encontrado"));
    }

    public void eliminar(Long id) {
        local_Organizador_Repository.deleteById(id);
    }

    // Transformer methods
    private Local_Organizador_DTO toDTO(Local_Organizador local_Organizador) {
        return new Local_Organizador_DTO(
                local_Organizador.getId(),
                local_Organizador.getLocal().getId(),
                local_Organizador.getOrganizador().getId(),
                local_Organizador.getCargo(),
                local_Organizador.getFechaAsignacion(),
                true // Asumiendo estado siempre true, o agregar campo si falta
        );
    }

    private Local_Organizador fromDTO(Local_Organizador_DTO dto) {
        Local local = localRepository.findById(dto.localId())
                .orElseThrow(() -> new RuntimeException("Local no encontrado"));
        Organizador organizador = organizadorRepository.findById(dto.organizadorId())
                .orElseThrow(() -> new RuntimeException("Organizador no encontrado"));
        Local_Organizador local_Organizador = new Local_Organizador();
        local_Organizador.setLocal(local);
        local_Organizador.setOrganizador(organizador);
        local_Organizador.setCargo(dto.cargo());
        local_Organizador.setFechaAsignacion(dto.fechaAsignacion());
        return local_Organizador;
    }

    public Local_Organizador_Repository getLocal_Organizador_Repository() {
        return local_Organizador_Repository;
    }
}
