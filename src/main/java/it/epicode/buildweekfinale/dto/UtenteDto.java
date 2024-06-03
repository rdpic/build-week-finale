package it.epicode.buildweekfinale.dto;

import it.epicode.buildweekfinale.enums.RuoloUtente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UtenteDto {

    @NotNull
    private String username;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String nome;

    @NotNull
    private String cognome;

    private String avatar;

    @NotNull
    private String password;

    private RuoloUtente ruoloUtente;

    public UtenteDto(String username, String email, String nome, String cognome, String password) {
        this.username = username;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.password =  password;
        this.ruoloUtente = RuoloUtente.USER;
    }

}
