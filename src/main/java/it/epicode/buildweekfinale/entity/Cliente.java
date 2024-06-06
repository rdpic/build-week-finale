package it.epicode.buildweekfinale.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
@Data
@Entity
@Table(name = "clienti")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ragioneSociale;
    private String partitaIVA;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private BigDecimal fatturatoAnnuale;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Indirizzo> indirizzi = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Fattura> fatture = new ArrayList<>();

}
