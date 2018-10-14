package br.com.openenade.api;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = AnoController.ENDPOINT)
public class AnoController {

    public static final String ENDPOINT = "/ano";

    @Autowired
    private AnoService service;

    @GetMapping
    public Collection<Ano> getAnos() {

        return service.getAllAnos();
    }

    @GetMapping(path = "/{ano}")
    public Ano getAno(@PathVariable(name = "ano") Integer ano) {

        return service.getAno(ano);
    }

    @PostMapping
    public ResponseEntity<String> saveAno(@RequestBody Ano ano) {
        try {
            service.addAno(ano);
            return ResponseEntity.status(HttpStatus.OK).body("{\"Response\": \" OK \"}");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"Response\": \"" + e.getLocalizedMessage() + "\"}");
        }
    }

    @PutMapping
    public ResponseEntity<String> updateAno(@RequestBody Ano ano) {

        return this.saveAno(ano);
    }

    @DeleteMapping(path = "/{ano}")
    public ResponseEntity<String> updateAno(@PathVariable(name = "ano") Integer ano) {

        try {
            service.deleteAno(ano);
            return ResponseEntity.status(HttpStatus.OK).body("{\"Response\": \" OK \"}");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"Response\": \"" + e.getLocalizedMessage() + "\"}");
        }
    }
}
