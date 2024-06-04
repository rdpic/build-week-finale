package it.epicode.buildweekfinale.controller;

import it.epicode.buildweekfinale.service.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/importa-province")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    public ResponseEntity<String> importaProvince(@RequestParam("file") MultipartFile file) {
        try {
            provinciaService.importaProvince(file);
            return ResponseEntity.ok("Province importate con successo");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Errore durante l'importazione delle province.");
        }
    }

}
