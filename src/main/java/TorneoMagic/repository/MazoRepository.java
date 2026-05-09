package TorneoMagic.repository;

import TorneoMagic.model.Mazo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MazoRepository extends JpaRepository<Mazo, Long> {
}