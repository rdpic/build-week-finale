package it.epicode.buildweekfinale.entity;

import it.epicode.buildweekfinale.enums.TipoIndirizzo;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "indirizzi")
public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String via;
    private String civico;
    private String localita;
    private String cap;
    private String comune;

    @Enumerated(EnumType.STRING)
    private TipoIndirizzo tipoIndirizzo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}