package it.epicode.buildweekfinale.repository;

import it.epicode.buildweekfinale.entity.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Integer> {
}
