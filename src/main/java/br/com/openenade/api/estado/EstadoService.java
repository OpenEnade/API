package br.com.openenade.api.estado;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public Collection<Estado> getAllEstados() {

        return this.repository.findAll();
    }
    
    public Estado getEstado(String sigla) {

        return this.repository.findBySigla(sigla);
    }

    public void addEstado(Estado estado) {

        this.repository.save(estado);
    }

    public void updateAno(Estado estado) {

        this.addEstado(estado);
    }

    public void deleteAno(String sigla) {

        this.repository.deleteBySigla(sigla);
    }

  
}
