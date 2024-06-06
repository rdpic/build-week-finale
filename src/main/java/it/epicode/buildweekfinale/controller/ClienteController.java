package it.epicode.buildweekfinale.controller;

import it.epicode.buildweekfinale.entity.Cliente;
import it.epicode.buildweekfinale.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClienti() {
        return clienteService.getAllClienti();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        return clienteService.getClienteById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente createdCliente = clienteService.saveCliente(cliente);
        return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente dettagliCliente) {
        Optional<Cliente> clienteToUpdate = clienteService.updateCliente(id, dettagliCliente);

        if (clienteToUpdate.isPresent()) {
            return ResponseEntity.ok().body(clienteToUpdate.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }

}
