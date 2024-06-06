package it.epicode.buildweekfinale.service;

import it.epicode.buildweekfinale.dto.UtenteLoginDto;
import it.epicode.buildweekfinale.entity.Utente;
import it.epicode.buildweekfinale.exception.NotFoundException;
import it.epicode.buildweekfinale.exception.UnauthorizedException;
import it.epicode.buildweekfinale.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String authenticateUtenteAndGenerateToken(UtenteLoginDto utenteLoginDto) {
        Optional<Utente> utenteOptional = utenteService.getUtenteByUsername(utenteLoginDto.getUsername());

        if (utenteOptional.isPresent()) {
            Utente utente = utenteOptional.get();
            if (passwordEncoder.matches(utenteLoginDto.getPassword(), utente.getPassword())) {
                return jwtTool.createToken(utente);
            } else {
                throw new UnauthorizedException("Errore in fase di login, ritentare.");
            }

        } else {
            throw new NotFoundException("Utente con username: " + utenteLoginDto.getUsername() + "non trovato.");
        }
    }
}
