package br.com.openenade.api.curso;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;
    
    public Curso getCursoByCodigo(Long codigo) {
        return repository.findById(codigo).get();
    }
    
}
