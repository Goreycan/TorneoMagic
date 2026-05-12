package TorneoMagic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import TorneoMagic.model.Comuna;

@Repository
public interface  ComunaRepository extends JpaRepository<Comuna, Long> {

}
