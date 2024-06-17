package it.epicode.buildweekfinale.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@SuppressWarnings("all")
@Data
public class ClienteDto {

    @NotNull
    private String ragioneSociale;

    @NotNull
    private String partitaIVA;

    @Email
    @NotNull
    private String email;

    @NotNull
    private LocalDate dataInserimento;

    @NotNull
    private LocalDate dataUltimoContatto;

    @NotNull
    private BigDecimal fatturatoAnnuale;

    private String pec;

    @NotNull
    private String telefono;

    @Email
    @NotNull
    private String emailContatto;

    private String nomeContatto;

    private String cognomeContatto;

    @NotNull
    private String telefonoContatto;

    private String logoAziendale;

    public ClienteDto(String ragioneSociale, String partitaIVA, String email, LocalDate dataInserimento, LocalDate dataUltimoContatto, BigDecimal fatturatoAnnuale, String telefono, String emailContatto, String telefonoContatto) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIVA = partitaIVA;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.telefono = telefono;
        this.emailContatto = emailContatto;
        this.telefonoContatto = telefonoContatto;
    }

}
