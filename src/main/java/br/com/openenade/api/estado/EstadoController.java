package br.com.openenade.api.estado;

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
import br.com.openenade.api.utils.GeneralUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = EstadoController.ENDPOINT)
public class EstadoController {

    public static final String ENDPOINT = "/estado";
    
    public static final String SIGLA_ID = "sigla";

    @Autowired
    private EstadoService service;


    @GetMapping
    public Collection<Estado> getEstados() {

        return service.getAllEstados();
    }

    @GetMapping(path = "/{" + SIGLA_ID + "}")
    public Estado getEstado(@PathVariable(name = SIGLA_ID) String sigla) {

        return service.getEstado(sigla);
    }

    @PostMapping
    public ResponseEntity<String> saveEstado(@RequestBody Estado estado) {

        try {
            service.addEstado(estado);
            return GeneralUtils.getResponseEntity(HttpStatus.OK, "OK");

        } catch (Exception e) {

            return GeneralUtils.getResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());

        }
    }

    @PutMapping
    public ResponseEntity<String> updateAno(@RequestBody Estado estado) {

        return this.saveEstado(estado);
    }

    @DeleteMapping(path = "/{" + SIGLA_ID + "}")
    public ResponseEntity<String> updateAno(@PathVariable(name = SIGLA_ID) String sigla) {

        try {
            service.deleteAno(sigla);
            return GeneralUtils.getResponseEntity(HttpStatus.OK, "OK");

        } catch (Exception e) {

            return GeneralUtils.getResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
