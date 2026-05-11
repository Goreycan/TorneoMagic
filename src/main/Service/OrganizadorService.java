package TorneoMagic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import TorneoMagic.model.Organizador;
import TorneoMagic.repository.OrganizadorRepository;

@Service
public class OrganizadorService {
    private final OrganizadorRepository organizadorRepository;

    public OrganizadorService(OrganizadorRepository organizadorRepository) {
        this.organizadorRepository = organizadorRepository;
    }

    public List<Organizador> listar() {
        return organizadorRepository.findAll();
    }

    public Optional<Organizador> obtenerPorId(Long id) {
        return organizadorRepository.findById(id);
    }

    public Organizador crear(Organizador organizador) {
        return organizadorRepository.save(organizador);
    }

    public Organizador actualizar(Long id, Organizador organizador) {
        return organizadorRepository.findById(id).map(organizadorExistente -> {
            organizadorExistente.setNombre(organizador.getNombre());
            organizadorExistente.setApellido(organizador.getApellido());
            organizadorExistente.setEmail(organizador.getEmail());
            organizadorExistente.setTelefono(organizador.getTelefono());
            return organizadorRepository.save(organizadorExistente);
        }).orElseThrow(() -> new RuntimeException("Organizador no encontrado"));
    }

    public void eliminar(Long id) {
        organizadorRepository.deleteById(id);
    }
}
