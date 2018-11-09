package br.com.openenade.api.nota;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = NotaController.ENDPOINT)
public class NotaController {

    public static final String ENDPOINT = "notas";

    @Autowired
    private NotaService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Nota>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

}
