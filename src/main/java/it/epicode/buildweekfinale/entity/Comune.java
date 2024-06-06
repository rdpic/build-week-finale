package it.epicode.buildweekfinale.entity;

import jakarta.persistence.*;
import lombok.Data;

@SuppressWarnings("all")
@Data
@Entity
@Table(name = "comuni")
public class Comune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codiceProvincia;
    private String progressivo;
    private String denominazione;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

}
