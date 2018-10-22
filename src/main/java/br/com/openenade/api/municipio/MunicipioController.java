package br.com.openenade.api.municipio;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = MunicipioController.ENDPOINT)
public class MunicipioController {

    public static final String ENDPOINT = "municipio";
    
    @Autowired
    private MunicipioService service;
    
    @PostMapping
    public void postMunicipio(@Valid @RequestBody Municipio municipio) {
        this.service.save(municipio);
    }
    
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Municipio>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    
    @ResponseBody
    @GetMapping(path = "/{codigo}")
    public ResponseEntity<Municipio> getMunicipioByCodigo
                (@PathVariable(name = "codigo") Long codigo){
        
        Optional<Municipio> optMunicipio = this.service.getByCodigo(codigo);
        
        if(optMunicipio.isPresent()) {
            return new ResponseEntity<>(optMunicipio.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping(path = "/{codigo}")
    public ResponseEntity<Municipio> deleteMunicipio
                (@PathVariable(name = "codigo") Long codigo){
        
        try {
            service.deleteMunicipioByCodigo(codigo);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
