package br.com.openenade.api.modalidade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = ModalidadeController.ENDPOINT)
public class ModalidadeController {

    public static final String ENDPOINT = "/modalidades";

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<Modalidade[]> index() {
        return new ResponseEntity<>(Modalidade.values(), HttpStatus.OK);
    }

}
