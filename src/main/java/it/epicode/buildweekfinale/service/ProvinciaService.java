package it.epicode.buildweekfinale.service;

import it.epicode.buildweekfinale.entity.Provincia;
import it.epicode.buildweekfinale.repository.ProvinciaRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void importaProvince(MultipartFile file) {
        try {
            File tempFile = File.createTempFile("temp", ".csv");
            file.transferTo(tempFile);

            List<String> lines = FileUtils.readLines(tempFile, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line.startsWith("Sigla;Provincia;Regione")) {
                    continue;
                }
                String[] dati = line.split(";");
                Provincia provincia = new Provincia();
                provincia.setSigla(dati[0]);
                provincia.setNome(dati[1]);
                provincia.setRegione(dati[2]);
                provinciaRepository.save(provincia);
            }

            FileUtils.forceDelete(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
