package br.com.openenade.api.nota;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.modalidade.Modalidade;

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
    @GetMapping(path = "/{ano}-{codigoCurso}-{modalidade}-{codigoIES}-{codigoMunicipio}")
    public ResponseEntity<Nota> getNotaByIndex(NotaIdInterface notaIdInterface) {

        Optional<Nota> optNota = this.service.getNota(notaIdInterface);

        return new ResponseEntity<>(optNota.get(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{ano}-{codigoCurso}-{modalidade}-{codigoIES}-{codigoMunicipio}")
    public ResponseEntity<String> deleteNotaByIndex(NotaIdInterface notaIdInterface) {

        this.service.deleteNota(notaIdInterface);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filterby")
    public Collection<Nota> getFilteredNotas(NotaFilterInterface notaFilterInterface) {

        return this.service.filterByGenericAtribute(notaFilterInterface);
    }
    
}
