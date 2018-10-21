package br.com.openenade.api.estado;

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
@RequestMapping(path = EstadoController.ENDPOINT)
public class EstadoController {

    public static final String ENDPOINT = "/estado"  ;
    
    @Autowired
    private EstadoService service;
    
    @PostMapping
    public void postEstado(@Valid @RequestBody Estado estado) {
        this.service.save(estado);
    }
    
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Estado>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    
    @ResponseBody
    @GetMapping(path = "/{siglaEstado}")
    public ResponseEntity<Estado> getEstadoBySigla( 
            @PathVariable(name = "siglaEstado") String siglaEstado){
        
        Optional<Estado> optEstado = this.service.getBySiglaEstado(siglaEstado);
        
        if(optEstado.isPresent()) {
            return new ResponseEntity<>(optEstado.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
    
    @DeleteMapping(path = "/{siglaEstado}")
    public ResponseEntity<Estado> deleteEstadoBySigla(
            @PathVariable(name = "siglaEstado") String siglaEstado){
        try {
            service.deleteEstadoBySiglaEstado(siglaEstado);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
