package it.epicode.buildweekfinale.repository;

import it.epicode.buildweekfinale.entity.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FatturaRepository extends JpaRepository<Fattura, Integer> {
}
