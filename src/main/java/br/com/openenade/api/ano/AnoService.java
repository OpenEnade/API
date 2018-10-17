package br.com.openenade.api.ano;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnoService {

    @Autowired
    private AnoRepository repository;

    public Collection<Ano> getAllAnos() {

        return this.repository.findAll();
    }
    
    public Ano getAno(Integer ano) {

        return this.repository.findByAno(ano);
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
