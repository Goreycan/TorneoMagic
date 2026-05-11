package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.model.Local_Organizador;
import TorneoMagic.repository.Local_Organizador_Repository;

@Service
public class Local_Organizador_Service {
    private final Local_Organizador_Repository local_Organizador_Repository;

    public Local_Organizador_Service(Local_Organizador_Repository local_Organizador_Repository) {
        this.local_Organizador_Repository = local_Organizador_Repository;
    }

    public List<Local_Organizador> listar() {
        return local_Organizador_Repository.findAll();
    }

    public Optional<Local_Organizador> obtenerPorId(Long id) {
        return local_Organizador_Repository.findById(id);
    }

    public Local_Organizador crear(Local_Organizador local_Organizador) {
        return local_Organizador_Repository.save(local_Organizador);
    }

    public Local_Organizador actualizar(Long id, Local_Organizador local_Organizador) {
        return local_Organizador_Repository.findById(id).map(localOrganizadorExistente -> {
            localOrganizadorExistente.setLocal(local_Organizador.getLocal());
            localOrganizadorExistente.setOrganizador(local_Organizador.getOrganizador());
            localOrganizadorExistente.setCargo(local_Organizador.getCargo());
            localOrganizadorExistente.setFechaAsignacion(local_Organizador.getFechaAsignacion());
            return local_Organizador_Repository.save(localOrganizadorExistente);
        }).orElseThrow(() -> new RuntimeException("Local_Organizador no encontrado"));
    }

    public void eliminar(Long id) {
        local_Organizador_Repository.deleteById(id);
    }
}
