package it.epicode.buildweekfinale.service;

import it.epicode.buildweekfinale.dto.IndirizzoDto;
import it.epicode.buildweekfinale.entity.Cliente;
import it.epicode.buildweekfinale.entity.Comune;
import it.epicode.buildweekfinale.entity.Indirizzo;
import it.epicode.buildweekfinale.exception.BadRequestException;
import it.epicode.buildweekfinale.repository.ClienteRepository;
import it.epicode.buildweekfinale.repository.ComuneRepository;
import it.epicode.buildweekfinale.repository.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndirizzoService {

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ComuneRepository comuneRepository;

    public List<Indirizzo> getAllIndirizzi() {
        return indirizzoRepository.findAll();
    }

    public Optional<Indirizzo> getIndirizzoById(Integer id) {
        return indirizzoRepository.findById(id);
    }

    public Optional<Indirizzo> getIndirizzoByVia(String via) {
        return indirizzoRepository.findByVia(via);
    }

    public String saveIndirizzo(IndirizzoDto indirizzoDto) {
        if (getIndirizzoByVia(indirizzoDto.getVia()).isEmpty()) {
            Indirizzo indirizzo = new Indirizzo();
            indirizzo.setVia(indirizzoDto.getVia());
            indirizzo.setCivico(indirizzoDto.getCivico());
            indirizzo.setLocalita(indirizzoDto.getLocalita());
            indirizzo.setCap(indirizzoDto.getCap());
            indirizzo.setTipoIndirizzo(indirizzoDto.getTipoIndirizzo());

            Optional<Cliente> clienteOptional = clienteRepository.findById(indirizzoDto.getClienteId());
            if (clienteOptional.isEmpty()) {
                throw new BadRequestException("Il cliente con ID " + indirizzoDto.getClienteId() + " non esiste.");
            }

            Cliente cliente = clienteOptional.get();
            indirizzo.setCliente(cliente);

            Optional<Comune> comuneOptional = comuneRepository.findById(indirizzoDto.getComuneId());
            if (comuneOptional.isEmpty()) {
                throw new BadRequestException("Il comune con ID " + indirizzoDto.getComuneId() + " non esiste.");
            }

            Comune comune = comuneOptional.get();
            indirizzo.setComune(comune);

            indirizzoRepository.save(indirizzo);

            return "Indirizzo con ID " + indirizzo.getId() + " creato con successo.";
        } else {
            throw new BadRequestException("L'indirizzo esiste già.");
        }
    }

    public void deleteIndirizzo(Integer id) {
        indirizzoRepository.deleteById(id);
    }

    public String updateIndirizzo(Integer id, IndirizzoDto dettagliIndirizzo) {
        Optional<Indirizzo> indirizzoOptional = indirizzoRepository.findById(id);
        if (indirizzoOptional.isPresent()) {
            Indirizzo indirizzo = indirizzoOptional.get();
            indirizzo.setVia(dettagliIndirizzo.getVia());
            indirizzo.setCivico(dettagliIndirizzo.getCivico());
            indirizzo.setLocalita(dettagliIndirizzo.getLocalita());
            indirizzo.setCap(dettagliIndirizzo.getCap());
            indirizzo.setTipoIndirizzo(dettagliIndirizzo.getTipoIndirizzo());

            Optional<Cliente> clienteOptional = clienteRepository.findById(dettagliIndirizzo.getClienteId());
            if (clienteOptional.isEmpty()) {
                throw new BadRequestException("Il cliente con ID " + dettagliIndirizzo.getClienteId() + " non esiste.");
            }

            Cliente cliente = clienteOptional.get();
            indirizzo.setCliente(cliente);

            Optional<Comune> comuneOptional = comuneRepository.findById(dettagliIndirizzo.getComuneId());
            if (comuneOptional.isEmpty()) {
                throw new BadRequestException("Il comune con ID " + dettagliIndirizzo.getComuneId() + " non esiste.");
            }

            Comune comune = comuneOptional.get();
            indirizzo.setComune(comune);

            indirizzoRepository.save(indirizzo);

            return "Indirizzo con ID " + indirizzo.getId() + " modificato con successo.";
        } else {
            return "L'indirizzo con ID " + id + " non esiste.";
        }
    }
}
