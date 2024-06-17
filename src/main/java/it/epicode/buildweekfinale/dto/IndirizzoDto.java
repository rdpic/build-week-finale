package it.epicode.buildweekfinale.dto;

import it.epicode.buildweekfinale.enums.TipoIndirizzo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@SuppressWarnings("all")
@Data
public class IndirizzoDto {

    @NotNull
    private String via;

    @NotNull
    private String civico;

    @NotNull
    private String localita;

    @NotNull
    private String cap;

    @NotNull
    private TipoIndirizzo tipoIndirizzo;

    @NotNull
    private Integer clienteId;

    @NotNull
    private Integer comuneId;

    public IndirizzoDto(String via, String civico, String localita, String cap, TipoIndirizzo tipoIndirizzo, Integer clienteId, Integer comuneId) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.tipoIndirizzo = tipoIndirizzo;
        this.clienteId = clienteId;
        this.comuneId = comuneId;
    }

}
