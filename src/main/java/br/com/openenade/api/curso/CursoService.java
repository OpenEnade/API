package br.com.openenade.api.curso;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public void save(Curso curso) {
        this.repository.save(curso);
    }
    
    public List<Curso> getAll() {
        return this.repository.findAll();
    }
    
    public Optional<Curso> getByCodigo(Long codigo) {
        return this.repository.findById(codigo);
    }

}
