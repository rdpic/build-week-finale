package it.epicode.buildweekfinale.repository;

import it.epicode.buildweekfinale.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    Optional<Utente> findByUsername(String username);

}
