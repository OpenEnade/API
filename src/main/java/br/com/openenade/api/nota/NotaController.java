package br.com.openenade.api.nota;

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
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.universidade.Universidade;

@RestController
@CrossOrigin("*")
@RequestMapping(path = NotaController.ENDPOINT)
public class NotaController {

    public static final String ENDPOINT = "notas";

    @Autowired
    private NotaService service;

    @PostMapping
    public void postNota(@Valid @RequestBody Nota nota) {
        this.service.save(nota);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Nota>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(path = "/{ano}/{codigoCurso}/{codigoIES}")
    public ResponseEntity<Nota> getNotaByIndex(@PathVariable(name = "ano") Integer ano,
            @PathVariable(name = "codigoCurso") Long codigoCurso,
            @PathVariable(name = "codigoIES") Long codigoIES) {

        Ano anoObj = new Ano();
        anoObj.setAno(ano);

        Curso curso = new Curso();
        curso.setCodigoCurso(codigoCurso);

        Universidade universidade = new Universidade();
        universidade.setCodigoIES(codigoIES);

        Optional<Nota> optNota = this.service.getNotaById(new NotaId(anoObj, curso, universidade));

        if (optNota.isPresent()) {
            return new ResponseEntity<>(optNota.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(path = "/{ano}/{codigoCurso}/{codigoIES}")
    public ResponseEntity<String> deleteNotaByIndex(@PathVariable(name = "ano") Integer ano,
            @PathVariable(name = "codigoCurso") Long codigoCurso,
            @PathVariable(name = "codigoIES") Long codigoIES) {

        Ano anoObj = new Ano();
        anoObj.setAno(ano);

        Curso curso = new Curso();
        curso.setCodigoCurso(codigoCurso);

        Universidade universidade = new Universidade();
        universidade.setCodigoIES(codigoIES);

        boolean deleted = this.service.deleteNotaById(new NotaId(anoObj, curso, universidade));

        if (deleted) {
            return new ResponseEntity<>("Deletado.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Index não encontrado.", HttpStatus.NOT_FOUND);
        }
    }

}
