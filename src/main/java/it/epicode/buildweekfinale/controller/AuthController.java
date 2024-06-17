package it.epicode.buildweekfinale.controller;

import it.epicode.buildweekfinale.dto.AuthDataDto;
import it.epicode.buildweekfinale.dto.SignupDto;
import it.epicode.buildweekfinale.dto.UtenteDto;
import it.epicode.buildweekfinale.dto.UtenteLoginDto;
import it.epicode.buildweekfinale.exception.BadRequestException;
import it.epicode.buildweekfinale.service.AuthService;
import it.epicode.buildweekfinale.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("all")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/auth/signup")
    public SignupDto signup(@RequestBody @Validated UtenteDto utenteDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }

        return utenteService.saveUtente(utenteDto);
    }

    @PostMapping("/auth/login")
    public AuthDataDto login(@RequestBody @Validated UtenteLoginDto utenteLoginDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }

        return authService.authenticateUtenteAndGenerateToken(utenteLoginDTO);

    }
}