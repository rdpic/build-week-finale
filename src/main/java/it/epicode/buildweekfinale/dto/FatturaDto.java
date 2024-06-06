package it.epicode.buildweekfinale.dto;

import it.epicode.buildweekfinale.enums.StatoFattura;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FatturaDto {

    @NotNull
    private LocalDate data;

    @NotNull
    private BigDecimal importo;

    @NotNull
    private String numero;

    private StatoFattura statoFattura;

    @NotNull
    private Integer clienteId;

    public FatturaDto(LocalDate data, BigDecimal importo, String numero, Integer clienteId) {
        this.data = data;
        this.importo = importo;
        this.numero = numero;
        this.statoFattura = StatoFattura.INVIATA;
        this.clienteId = clienteId;
    }

}
