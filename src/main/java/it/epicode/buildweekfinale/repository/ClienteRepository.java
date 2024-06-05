package it.epicode.buildweekfinale.repository;

import it.epicode.buildweekfinale.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c ORDER BY c.ragioneSociale ASC")
    List<Cliente> elencaClientiPerNome();

    @Query("SELECT c FROM Cliente c ORDER BY c.fatturatoAnnuale DESC")
    List<Cliente> elencaClientiPerFatturato();

    @Query("SELECT c FROM Cliente c ORDER BY c.dataInserimento DESC")
    List<Cliente> elencaClientiPerDataInserimento();

    @Query("SELECT c FROM Cliente c ORDER BY c.dataUltimoContatto DESC")
    List<Cliente> elencaClientiPerDataUltimoContatto();


    /*@Query("SELECT c FROM Cliente c JOIN FETCH c.indirizzi i WHERE i.tipoIndirizzo = 'SEDE_LEGALE' ORDER BY i.comune.provincia.nome ASC")
    List<Cliente> elencaClientiPerProvinciaSedeLegale();*/

    @Query("SELECT c FROM Cliente c JOIN c.indirizzi i JOIN i.comune com JOIN com.provincia p WHERE i.tipoIndirizzo = 'SEDE_LEGALE' ORDER BY p.nome ASC")
    List<Cliente> elencaClientiPerProvinciaSedeLegale();


    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale > :minRevenue AND c.fatturatoAnnuale < :maxRevenue")
    List<Cliente> findByFatturatoAnnuo(@Param("minRevenue") BigDecimal minRevenue, @Param("maxRevenue") BigDecimal maxRevenue);


    @Query("SELECT c FROM Cliente c WHERE c.dataInserimento >= :startDate")
    List<Cliente> findByDataInserimento(@Param("startDate") LocalDate startDate);

    @Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto <= :endDate")
    List<Cliente> findByDataUltimoContatto(@Param("endDate") LocalDate endDate);

    @Query("SELECT c FROM Cliente c WHERE c.ragioneSociale LIKE %:partialName%")
    List<Cliente> findByNomeParziale(@Param("partialName") String partialName);
}
