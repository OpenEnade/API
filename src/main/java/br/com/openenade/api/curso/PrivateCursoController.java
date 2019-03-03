package br.com.openenade.api.curso;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.openenade.api.modalidade.ModalidadeService;

@RestController
@RequestMapping(path = PublicCursoController.ENDPOINT)
public class PrivateCursoController {

    @Autowired
    private CursoService service;

    @ResponseBody
    @PostMapping
    public Curso addCurso(@Valid @RequestBody Curso newCurso) {
        return this.service.addCurso(newCurso);
    }

    @DeleteMapping(path = "/{codigo}/{modalidade}")
    public void deleteCursoByCodigo(@PathVariable(name = "codigo") Long codigo,
            @PathVariable(name = "modalidade") Integer modalidadeId) {
        this.service
                .deleteById(new CursoId(codigo, ModalidadeService.getModalidadeById(modalidadeId)));
    }

}
