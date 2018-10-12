package br.com.openenade.api;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.openenade.api.Ano;
import br.com.openenade.api.AnoRepository;

@Service
@Transactional
public class AnoService {

    @Autowired
    private AnoRepository repository;

    public Collection<Ano> getAllAnos() {

        return this.repository.findAll();
    }
    
    public Ano getAno(Integer ano) {

        return this.repository.getByAno(ano);
    }

    public void addAno(Ano ano) {

        this.repository.save(ano);
    }

    public void updateAno(Ano ano) {

        this.addAno(ano);
    }

    public void deleteAno(Integer ano) {

        this.repository.deleteByAno(ano);
    }

  
}
