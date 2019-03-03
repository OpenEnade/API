package br.com.openenade.api.curso;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.modalidade.Modalidade;
import br.com.openenade.api.modalidade.ModalidadeService;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public Curso addCurso(Curso curso) {
        return this.repository.save(curso);
    }

    public List<Curso> getAll() {
        return this.repository.findAll();
    }

    public Curso getCursoByCursoId(CursoId cursoId) {
        Optional<Curso> optCurso = this.repository.findById(cursoId);
        if (optCurso.isPresent()) {
            return optCurso.get();
        } else {
            throw new ResourceNotFound(
                    "Cannot find Curso with Codigo [" + Long.toString(cursoId.getCodigoArea())
                            + "] and Modalidade [" + cursoId.getModalidade().getValue() + "]");
        }
    }

    public Curso getCursoByCodigo(Long codigo, Integer modalidade) {
        CursoId cursoId = new CursoId(codigo, ModalidadeService.getModalidadeById(modalidade));
        return this.getCursoByCursoId(cursoId);
    }

    public Curso getCursoByCodigo(Long codigo, Modalidade modalidade) {
        CursoId cursoId = new CursoId(codigo, modalidade);
        return this.getCursoByCursoId(cursoId);
    }

    public void deleteById(CursoId cursoId) {
        this.getCursoByCodigo(cursoId.getCodigoArea(), cursoId.getModalidade());
        this.repository.deleteById(cursoId);
    }

}
