package it.epicode.buildweekfinale.service;

import it.epicode.buildweekfinale.entity.Indirizzo;
import it.epicode.buildweekfinale.repository.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    private List<Indirizzo> getAllIndirizzi() {
        return indirizzoRepository.findAll();
    }

    private Optional<Indirizzo> getIndirizzoById(Integer id) {
        return indirizzoRepository.findById(id);
    }

    private Indirizzo saveIndirizzo(Indirizzo indirizzo) {
        return indirizzoRepository.save(indirizzo);
    }

    private void deleteIndirizzo(Integer id) {
        indirizzoRepository.deleteById(id);
    }

}
