package it.epicode.buildweekfinale.controller;

import it.epicode.buildweekfinale.dto.FatturaDto;
import it.epicode.buildweekfinale.entity.Cliente;
import it.epicode.buildweekfinale.entity.Fattura;
import it.epicode.buildweekfinale.service.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @GetMapping
    public List<Fattura> getAllFatture() {
        return fatturaService.getAllFatture();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fattura> getFatturaById(@PathVariable Integer id) {
        return fatturaService.getFatturaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createFattura(@RequestBody FatturaDto fatturaDto) {
        String createdFattura = fatturaService.saveFattura(fatturaDto);
        return new ResponseEntity<>(createdFattura, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateFattura(@PathVariable Integer id, @RequestBody FatturaDto dettagliFattura) {
        String updatedFattura = fatturaService.updateFattura(id, dettagliFattura);
        return new ResponseEntity<>(updatedFattura, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFattura(@PathVariable Integer id) {
        fatturaService.deleteFattura(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{nomeCliente}")
    public List<Fattura> getFattureByCliente(@PathVariable String nomeCliente) {
        return fatturaService.findByCliente(nomeCliente);
    }

    @GetMapping("/{stato}")
    public List<Fattura> getFattureByStato(@PathVariable String stato) {
        return fatturaService.findByStato(stato);
    }

    @GetMapping("/data")
    public List<Fattura> getFattureByData(@RequestParam String data) {
        return fatturaService.findByData(data);
    }

    @GetMapping("/{anno}")
    public List<Fattura> getFattureByAnno(@PathVariable Integer anno) {
        return fatturaService.findByAnno(anno);
    }

    @GetMapping("/by-importo")
    public List<Fattura> getFattureByImporto(@RequestParam BigDecimal minImporto, @RequestParam BigDecimal maxImporto) {
        return fatturaService.findByRangeImporto(minImporto, maxImporto);

    }
}
