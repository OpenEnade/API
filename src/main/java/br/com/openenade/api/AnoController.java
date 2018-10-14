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
    
    public static final String ANO_ID = "ano";

    @Autowired
    private AnoService service;


    @GetMapping
    public Collection<Ano> getAnos() {

        return service.getAllAnos();
    }

    @GetMapping(path = "/{" + ANO_ID + "}")
    public Ano getAno(@PathVariable(name = ANO_ID) Integer ano) {

        return service.getAno(ano);
    }

    @PostMapping
    public ResponseEntity<String> saveAno(@RequestBody Ano ano) {

        try {
            service.addAno(ano);
            return AnoUtils.getResponseEntity(HttpStatus.OK, "OK");

        } catch (Exception e) {

            return AnoUtils.getResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());

        }
    }

    @PutMapping
    public ResponseEntity<String> updateAno(@RequestBody Ano ano) {

        return this.saveAno(ano);
    }

    @DeleteMapping(path = "/{" + ANO_ID + "}")
    public ResponseEntity<String> updateAno(@PathVariable(name = ANO_ID) Integer ano) {

        try {
            service.deleteAno(ano);
            return AnoUtils.getResponseEntity(HttpStatus.OK, "OK");

        } catch (Exception e) {

            return AnoUtils.getResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
