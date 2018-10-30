package br.com.openenade.api.curso;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.openenade.api.modalidade.Modalidade;


@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public Curso save(Curso curso) {
        return this.repository.save(curso);
    }

    public List<Curso> getAll() {
        return this.repository.findAll();
    }

    public Optional<Curso> getByCodigo(Long codigo, Modalidade modalidade) {
        CursoId cursoId = new CursoId(codigo, modalidade);
        return this.repository.findById(cursoId);

    }

}
