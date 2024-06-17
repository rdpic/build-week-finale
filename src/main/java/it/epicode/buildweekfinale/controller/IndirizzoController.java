package it.epicode.buildweekfinale.controller;

import it.epicode.buildweekfinale.dto.IndirizzoDto;
import it.epicode.buildweekfinale.entity.Indirizzo;
import it.epicode.buildweekfinale.service.IndirizzoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @GetMapping
    public List<Indirizzo> getAllIndirizzi() {
        return indirizzoService.getAllIndirizzi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Indirizzo> getIndirizzoById(@PathVariable Integer id) {
        return indirizzoService.getIndirizzoById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createIndirizzo(@RequestBody IndirizzoDto indirizzoDto) {
        String createdIndirizzo = indirizzoService.saveIndirizzo(indirizzoDto);
        return new ResponseEntity<>(createdIndirizzo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateIndirizzo(@PathVariable Integer id, @RequestBody IndirizzoDto dettagliIndirizzo) {
        String indirizzoToUpdate = indirizzoService.updateIndirizzo(id, dettagliIndirizzo);
        return new ResponseEntity<>(indirizzoToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndirizzo(@PathVariable Integer id) {
        indirizzoService.deleteIndirizzo(id);
        return ResponseEntity.noContent().build();
    }

}
