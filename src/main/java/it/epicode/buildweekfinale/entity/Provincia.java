package it.epicode.buildweekfinale.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@SuppressWarnings("all")
@Data
@Entity
@Table(name = "province")
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sigla;
    private String nome;
    private String regione;

    @OneToMany(mappedBy = "provincia")
    private List<Comune> comuni;

}
