package br.com.openenade.api.curso;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.modalidade.Modalidade;

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

    public Curso getByCodigo(Long codigo, Modalidade modalidade) {
        CursoId cursoId = new CursoId(codigo, modalidade);
        Optional<Curso> optCurso = this.repository.findById(cursoId);
        if (optCurso.isPresent()) {
            return optCurso.get();
        } else {
            throw new ResourceNotFound("Cannot find Curso with Codigo [" + Long.toString(codigo)
                    + "] and Modalidade [" + modalidade.getValue() + "]");
        }
    }

    public void deleteById(CursoId cursoId) {
        this.getByCodigo(cursoId.getCodigoArea(), cursoId.getModalidade());
        this.repository.deleteById(cursoId);
    }

}
