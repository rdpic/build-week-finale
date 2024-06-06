package it.epicode.buildweekfinale.service;

import it.epicode.buildweekfinale.entity.Comune;
import it.epicode.buildweekfinale.repository.ComuneRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ComuneService {

    @Autowired
    private ComuneRepository comuneRepository;

    public void importaComuni(MultipartFile file) throws IOException {
        try {
            File tempFile = File.createTempFile("comuni", ".csv");
            file.transferTo(tempFile);

            List<String> lines = FileUtils.readLines(tempFile, "UTF-8");
            for (String line : lines) {
                if (line.startsWith("Codice Provincia (Storico)(1);Progressivo del Comune (2);Denominazione in italiano")) {
                    continue;
                }
                String[] dati = line.split(";");
                Comune comune = new Comune();
                comune.setCodiceProvincia(dati[0]);
                comune.setProgressivo(dati[1]);
                comune.setDenominazione(dati[2]);
                comuneRepository.save(comune);
            }

            FileUtils.forceDelete(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
