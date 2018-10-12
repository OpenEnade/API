package br.com.openenade.api.modalidade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modalidade")
public class ModalidadeController {

    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Modalidade[]> index() {
        return  new ResponseEntity<>(Modalidade.values(), HttpStatus.OK);
    }

}
