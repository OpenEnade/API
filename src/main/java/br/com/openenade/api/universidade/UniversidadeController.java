package br.com.openenade.api.universidade;

import java.util.List;
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
@RequestMapping(path = UniversidadeController.ENDPOINT)
public class UniversidadeController {

    public static final String ENDPOINT = "universidade";
    
    @Autowired
    private UniversidadeService service;
    
    @PostMapping
    public void postUniversidade(@Valid @RequestBody Universidade universidade) {
        this.service.save(universidade);
    }
    
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Universidade>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    
    @ResponseBody
    @GetMapping(path = "/{codigoIES}")
    public Universidade getUniversidadeByCodigoIES
                (@PathVariable(name = "codigoIES") Long codigoIES){
        
        return this.service.getUniversidadeByCodigoIES(codigoIES);
    }
    
    @DeleteMapping(path = "/{codigoIES}")
    public ResponseEntity<Universidade> deleteUniversidade
                (@PathVariable(name = "codigoIES") Long codigoIES){
        
        this.service.deleteUniversidadeByCodigoIES(codigoIES);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}