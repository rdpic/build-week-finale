package it.epicode.buildweekfinale.service;

import it.epicode.buildweekfinale.entity.Cliente;
import it.epicode.buildweekfinale.entity.Comune;
import it.epicode.buildweekfinale.entity.Indirizzo;
import it.epicode.buildweekfinale.enums.TipoIndirizzo;
import it.epicode.buildweekfinale.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("all")
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> updateCliente(Integer id, Cliente dettagliCliente) {
            Optional<Cliente> clienteOptional = clienteRepository.findById(id);
            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                cliente.setRagioneSociale(dettagliCliente.getRagioneSociale());
                cliente.setPartitaIVA(dettagliCliente.getPartitaIVA());
                cliente.setEmail(dettagliCliente.getEmail());
                cliente.setDataInserimento(dettagliCliente.getDataInserimento());
                cliente.setDataUltimoContatto(dettagliCliente.getDataUltimoContatto());
                cliente.setFatturatoAnnuale(dettagliCliente.getFatturatoAnnuale());
                cliente.setPec(dettagliCliente.getPec());
                cliente.setTelefono(dettagliCliente.getTelefono());
                cliente.setEmailContatto(dettagliCliente.getEmailContatto());
                cliente.setNomeContatto(dettagliCliente.getNomeContatto());
                cliente.setCognomeContatto(dettagliCliente.getCognomeContatto());
                cliente.setTelefonoContatto(dettagliCliente.getTelefonoContatto());
                cliente.setLogoAziendale(dettagliCliente.getLogoAziendale());
                clienteRepository.save(cliente);
                return Optional.of(cliente);
            } else {
                return Optional.empty();
            }
    }

    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

   /* public List<Cliente> elencaClientiPerProvinciaSedeLegale() {

        List<Cliente> clients = clienteRepository.findAll();

        return clients.stream()
                .sorted((c1, c2) -> {
                    Indirizzo sedeLegale1 = c1.getIndirizzi().stream()
                            .filter(indirizzo -> indirizzo.getTipoIndirizzo() == TipoIndirizzo.SEDE_LEGALE)
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("Cliente senza sede legale"));

                    Indirizzo sedeLegale2 = c2.getIndirizzi().stream()
                            .filter(indirizzo -> indirizzo.getTipoIndirizzo() == TipoIndirizzo.SEDE_LEGALE)
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("Cliente senza sede legale"));

                    String comuneNome1 = sedeLegale1.getComune();
                    String comuneNome2 = sedeLegale2.getComune();

                    return comuneNome1.compareTo(comuneNome2);
                })
                .collect(Collectors.toList());
    } */



}
