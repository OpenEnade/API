package br.com.openenade.api.curso;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.openenade.api.modalidade.Modalidade;


@RestController
@CrossOrigin("*")
@RequestMapping(path = CursoController.ENDPOINT)
public class CursoController {

    public static final String ENDPOINT = "curso";

    @Autowired
    private CursoService service;

    @PostMapping
    public Curso postCurso(@Valid @RequestBody Curso newCurso) {
        return this.service.save(newCurso);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Curso>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/{codigo}/{modalidade}")
    public ResponseEntity<Curso> getCursoByCodigo(@PathVariable(name = "codigo") Long codigo,
            @PathVariable(name = "modalidade") Modalidade modalidade) {
        Optional<Curso> optCurso = this.service.getByCodigo(codigo, modalidade);

        if (optCurso.isPresent()) {
            return new ResponseEntity<>(optCurso.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
