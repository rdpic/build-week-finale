package it.epicode.buildweekfinale.repository;

import it.epicode.buildweekfinale.entity.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
}
