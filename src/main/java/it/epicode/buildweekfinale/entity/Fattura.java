package it.epicode.buildweekfinale.entity;

import it.epicode.buildweekfinale.enums.StatoFattura;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("all")
@Entity
@Data
@Table(name = "fatture")
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate data;
    private BigDecimal importo;
    private String numero;

    @Enumerated(EnumType.STRING)
    private StatoFattura statoFattura;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}
