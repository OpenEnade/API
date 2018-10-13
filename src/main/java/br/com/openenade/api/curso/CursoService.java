package br.com.openenade.api.curso;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public Optional<Curso> getCursoByCodigo(Long codigo) {
        return this.repository.findById(codigo);
    }

    public void saveCurso(Curso curso) {
        this.repository.save(curso);
    }

}
