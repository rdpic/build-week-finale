package it.epicode.buildweekfinale.repository;

import it.epicode.buildweekfinale.entity.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndirizzoRepository extends JpaRepository<Indirizzo, Integer> {

    Optional<Indirizzo> findByVia(String via);

}
