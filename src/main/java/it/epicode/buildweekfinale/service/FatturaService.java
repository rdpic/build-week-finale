package it.epicode.buildweekfinale.service;

import it.epicode.buildweekfinale.dto.FatturaDto;
import it.epicode.buildweekfinale.entity.Cliente;
import it.epicode.buildweekfinale.entity.Fattura;
import it.epicode.buildweekfinale.enums.StatoFattura;
import it.epicode.buildweekfinale.exception.BadRequestException;
import it.epicode.buildweekfinale.exception.NotFoundException;
import it.epicode.buildweekfinale.repository.ClienteRepository;
import it.epicode.buildweekfinale.repository.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Fattura> getAllFatture() {
        return fatturaRepository.findAll();
    }

    public Optional<Fattura> getFatturaById(Integer id) {
        return fatturaRepository.findById(id);
    }

    public Optional<Fattura> getFatturaByNumero(String numero) {
        return fatturaRepository.findByNumero(numero);
    }

    public String saveFattura(FatturaDto fatturaDto) {
        if (getFatturaByNumero(fatturaDto.getNumero()).isEmpty()) {
            Fattura fattura = new Fattura();
            fattura.setData(fatturaDto.getData());
            fattura.setImporto(fatturaDto.getImporto());
            fattura.setNumero(fatturaDto.getNumero());
            fattura.setStatoFattura(fatturaDto.getStatoFattura());

            Optional<Cliente> clienteOptional = clienteRepository.findById(fatturaDto.getClienteId());
            if (clienteOptional.isEmpty()) {
                throw new BadRequestException("Il cliente con ID " + fatturaDto.getClienteId() + " non esiste.");
            }

            Cliente cliente = clienteOptional.get();
            fattura.setCliente(cliente);

            fatturaRepository.save(fattura);

            return "Fattura con ID " + fattura.getId() + " creata con successo.";
        } else {
            throw new BadRequestException("La fattura esiste già.");
        }
    }

    public String updateFattura(Integer id, FatturaDto dettagliFattura) {
        Optional<Fattura> fatturaOptional = fatturaRepository.findById(id);
        if (fatturaOptional.isPresent()) {
            Fattura fattura = fatturaOptional.get();
            fattura.setData(dettagliFattura.getData());
            fattura.setImporto(dettagliFattura.getImporto());
            fattura.setNumero(dettagliFattura.getNumero());
            fattura.setStatoFattura(dettagliFattura.getStatoFattura());

            Optional<Cliente> clienteOptional = clienteRepository.findById(dettagliFattura.getClienteId());
            if (clienteOptional.isEmpty()) {
                throw new BadRequestException("Il cliente con ID " + dettagliFattura.getClienteId() + " non esiste.");
            }

            Cliente cliente = clienteOptional.get();
            fattura.setCliente(cliente);

            fatturaRepository.save(fattura);

            return "Fattura con ID " + fattura.getId() + " modificata con successo.";
        } else {
            return "La fattura con ID " + id + " non esiste.";
        }
    }

    public void deleteFattura(Integer id) {
        fatturaRepository.deleteById(id);
    }

    public List<Fattura> findByCliente(String nomeCliente) {

        Optional<Cliente> clienteOpt = clienteRepository.findAll().stream().filter(c -> c.getRagioneSociale() == nomeCliente).findFirst();

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            return fatturaRepository.findByCliente(cliente);
        } else throw new NotFoundException("Cliente non trovato.");
    }

    public List<Fattura> findByStato(String stato) {

        try {
            StatoFattura statoFattura = StatoFattura.valueOf(stato);
            return fatturaRepository.findByStatoFattura(statoFattura);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return Collections.emptyList();
        }


    }

    public List<Fattura> findByData(String data) {
        try {
            LocalDate localDate = LocalDate.parse(data);
            return fatturaRepository.findByData(localDate);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    public List<Fattura> findByAnno(Integer anno) {
        try {
            return fatturaRepository.findByAnno(anno);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    public List<Fattura> findByRangeImporto(BigDecimal minImporto, BigDecimal maxImporto) {
        return fatturaRepository.findByRangeImporto(minImporto, maxImporto);
    }
}
