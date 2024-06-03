package it.epicode.buildweekfinale.service;

import it.epicode.buildweekfinale.entity.Fattura;
import it.epicode.buildweekfinale.repository.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;

    private List<Fattura> getAllFatture() {
        return fatturaRepository.findAll();
    }

    private Optional<Fattura> getFatturaById(Integer id) {
        return fatturaRepository.findById(id);
    }

    private void saveFattura(Fattura fattura) {
        fatturaRepository.save(fattura);
    }

    private void deleteFattura(Fattura fattura) {
        fatturaRepository.delete(fattura);
    }

}
