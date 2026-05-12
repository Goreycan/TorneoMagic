package TorneoMagic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import TorneoMagic.model.Local_Organizador;

@Repository
public interface Local_Organizador_Repository extends JpaRepository<Local_Organizador, Long> {

}
