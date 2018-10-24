package br.com.openenade.api.regiao;

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
@RequestMapping(path = RegiaoController.ENDPOINT)
public class RegiaoController {

    public static final String ENDPOINT = "regiao";

    @Autowired
    private RegiaoService service;

    @PostMapping
    public void postRegiao(@Valid @RequestBody Regiao regiao) {
        this.service.save(regiao);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Regiao>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/{sigla}")
    public ResponseEntity<Regiao> getRegiaoBySigla(@PathVariable(name = "sigla") String sigla) {
        return new ResponseEntity<>(this.service.getBySigla(sigla), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{sigla}")
    public ResponseEntity<Regiao> deleteRegiaoBySigla(@PathVariable(name = "sigla") String sigla) {
        this.service.deleteRegiaoBySigla(sigla);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
