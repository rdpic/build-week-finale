package it.epicode.buildweekfinale.service;

import it.epicode.buildweekfinale.dto.UtenteDto;
import it.epicode.buildweekfinale.entity.Utente;
import it.epicode.buildweekfinale.exception.BadRequestException;
import it.epicode.buildweekfinale.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public List<Utente> getAllUtenti() {
        return utenteRepository.findAll();
    }

    public Optional<Utente> getUtenteByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

    public Optional<Utente> getUtenteById(Integer id) {
        return utenteRepository.findById(id);
    }

    public String saveUtente(UtenteDto utenteDto) {
        if (getUtenteByUsername(utenteDto.getUsername()).isEmpty()) {
            Utente utente = new Utente();
            utente.setUsername(utenteDto.getUsername());
            utente.setEmail(utenteDto.getEmail());
            utente.setNome(utenteDto.getNome());
            utente.setCognome(utenteDto.getCognome());
            utente.setPassword(utenteDto.getPassword());
            utenteRepository.save(utente);

            return "Utente con ID" + utente.getId() + "creato con successo.";
        } else {
            throw new BadRequestException("Lo username è già stato preso.");
        }
    }

    public void deleteUtenteById(Integer id) {
        utenteRepository.deleteById(id);
    }

}
