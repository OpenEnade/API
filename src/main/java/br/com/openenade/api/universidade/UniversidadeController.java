package br.com.openenade.api.universidade;

import java.util.Collection;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.modalidade.Modalidade;

@RestController
@CrossOrigin("*")
@RequestMapping(path = UniversidadeController.ENDPOINT)
public class UniversidadeController {

    public static final String ENDPOINT = "universidades";

    @Autowired
    private UniversidadeService service;

    @PostMapping
    public void addUniversidade(@Valid @RequestBody Universidade universidade) {
        this.service.save(universidade);
    }

    @ResponseBody
    @GetMapping
    public ResponseEntity<Collection<Universidade>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/{codigoIES}")
    public ResponseEntity<Collection<Universidade>> getUniversidadesByCodigoIES(
            @PathVariable(name = "codigoIES") Long codigoIES) {

        return new ResponseEntity<>(this.service.getAllByCodigoIES(codigoIES), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/{codigoIES}/{codigoMunicipio}")
    public ResponseEntity<Universidade> getUniversidadeById(
            @PathVariable(name = "codigoIES") Long codigoIES,
            @PathVariable(name = "codigoMunicipio") Long codigoMunicipio) {

        return new ResponseEntity<>(service.getUniversidadeById(codigoIES, codigoMunicipio),
                HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/{codigoIES}/{codigoMunicipio}/cursos")
    public ResponseEntity<Collection<Curso>> getUniversidadeCursos(
            @PathVariable(name = "codigoIES") Long codigoIES,
            @PathVariable(name = "codigoMunicipio") Long codigoMunicipio) {

        return new ResponseEntity<>(
                this.service.getUniversidadeById(codigoIES, codigoMunicipio).getCursos(),
                HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/cursos")
    public ResponseEntity<Collection<Universidade>> getUniversidadesByCurso(
            @RequestParam Long codigoCurso, @RequestParam Modalidade modalidade) {
        Collection<Universidade> matchedUniversidades =
                this.service.getAllUniversidadesByCurso(codigoCurso, modalidade);

        return new ResponseEntity<>(matchedUniversidades, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{codigoIES}")
    public ResponseEntity<Universidade> deleteUniversidadesByCodigoCurso(
            @PathVariable(name = "codigoIES") Long codigoIES) {

        this.service.deleteUniversidadesByCodigoIES(codigoIES);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

