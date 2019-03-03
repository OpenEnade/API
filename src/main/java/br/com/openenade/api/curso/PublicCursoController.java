package br.com.openenade.api.curso;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.openenade.api.modalidade.ModalidadeService;

@RestController
@CrossOrigin("*")
@RequestMapping(path = PublicCursoController.ENDPOINT)
public class PublicCursoController {

    public static final String ENDPOINT = "cursos";

    @Autowired
    private CursoService service;

    @GetMapping
    @ResponseBody
    public List<Curso> getAll() {
        return this.service.getAll();
    }

    @ResponseBody
    @GetMapping(path = "/{codigo}/{modalidade}")
    public Curso getCursoByCodigo(@PathVariable(name = "codigo") Long codigo,
            @PathVariable(name = "modalidade") Integer modalidadeId) {
        return this.service.getByCodigo(codigo, ModalidadeService.getModalidadeById(modalidadeId));
    }

}
