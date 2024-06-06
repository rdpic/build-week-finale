package it.epicode.buildweekfinale.repository;

import it.epicode.buildweekfinale.entity.Cliente;
import it.epicode.buildweekfinale.entity.Fattura;
import it.epicode.buildweekfinale.enums.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FatturaRepository extends JpaRepository<Fattura, Integer> {

    @Query("SELECT f FROM Fattura f WHERE f.cliente = :cliente")
    List<Fattura> findByCliente(Cliente cliente);

    @Query("SELECT f FROM Fattura f WHERE f.statoFattura = :statoFattura")
    List<Fattura> findByStatoFattura(StatoFattura statoFattura);

    @Query("SELECT f FROM Fattura f WHERE f.data = :data")
    List<Fattura> findByData(LocalDate data);

    @Query("SELECT f FROM Fattura f WHERE YEAR(f.data) = :anno")
    List<Fattura> findByAnno(int anno);

    @Query("SELECT f FROM Fattura f WHERE f.importo BETWEEN :minImporto AND :maxImporto")
    List<Fattura> findByRangeImporto(BigDecimal minImporto, BigDecimal maxImporto);


}


