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
    @GetMapping(path = "/{index}")
    public ResponseEntity<Nota> getNotaByIndex(@PathVariable(name = "index") Integer index) {

        Optional<Nota> optNota = this.service.getNotaByIndex(index);

        if (optNota.isPresent()) {
            return new ResponseEntity<>(optNota.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{index}")
    public ResponseEntity<String> deleteNotaByIndex(@PathVariable(name = "index") Integer index) {
        boolean deleted = this.service.deleteNotaByIndex(index);

        if (deleted) {
            return new ResponseEntity<>("Deletado.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Index não encontrado.", HttpStatus.NOT_FOUND);
        }
    }
}
