package br.com.openenade.api.regiao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.openenade.api.estado.EstadoService;
import br.com.openenade.api.exceptions.ResourceNotFound;

@Service
@Transactional
public class RegiaoService {

    @Autowired
    private RegiaoRepository repository;

    @Autowired
    private EstadoService estadoService;

    public Regiao save(Regiao regiao) {
        return this.repository.save(regiao);
    }

    public List<Regiao> getAll() {
        return this.repository.findAll();
    }

    public Optional<Regiao> getOptionalBySigla(String sigla) {
        return this.repository.findById(sigla);
    }

    public Regiao getBySigla(String sigla) {
        Optional<Regiao> regiao = this.getOptionalBySigla(sigla);
        if (regiao.isPresent()) {
            return regiao.get();
        } else {
            throw new ResourceNotFound("Nao existe Regiao de sigla " + sigla);
        }
    }

    public void deleteRegiaoBySigla(String sigla) {

        this.getBySigla(sigla);
        this.estadoService.deleteEstadosByRegiaoSigla(sigla);
        this.repository.deleteById(sigla);
    }
}
