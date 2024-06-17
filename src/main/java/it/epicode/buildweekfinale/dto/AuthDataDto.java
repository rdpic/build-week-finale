package it.epicode.buildweekfinale.dto;

import it.epicode.buildweekfinale.entity.Utente;
import lombok.Data;

@Data
public class AuthDataDto {

    private String accessToken;
    private Utente utente;
}
