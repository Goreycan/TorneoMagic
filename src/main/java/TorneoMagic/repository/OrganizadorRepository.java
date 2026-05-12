package TorneoMagic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import TorneoMagic.model.Organizador;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {

}
