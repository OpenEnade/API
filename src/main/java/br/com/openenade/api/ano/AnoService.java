package br.com.openenade.api.ano;

import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.openenade.api.exceptions.ResourceNotFound;

@Service
@Transactional
public class AnoService {

    @Autowired
    private AnoRepository repository;

    public Collection<Ano> getAllAnos() {
        return this.repository.findAll();
    }

    public Ano getAno(Integer ano) {
        Optional<Ano> anoo = this.repository.findById(ano);
        if (anoo.isPresent()) {
            return anoo.get();
        } else {
            throw new ResourceNotFound("" + ano);
        }
    }

    public Ano addAno(Ano ano) {
        return this.repository.save(ano);
    }

    public Ano updateAno(Ano ano) {
        return this.repository.saveAndFlush(ano);
    }

    public void deleteAno(Integer ano) {
        this.getAno(ano);
        this.repository.deleteById(ano);
    }


}
