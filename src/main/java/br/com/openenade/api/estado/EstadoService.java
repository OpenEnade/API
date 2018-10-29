package br.com.openenade.api.estado;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.regiao.Regiao;
import br.com.openenade.api.regiao.RegiaoService;

@Service
@Transactional
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private RegiaoService regiaoService;

    public Estado save(Estado estado) {
        Optional<Regiao> optRegiao =
                this.regiaoService.getOptionalBySigla(estado.getRegiao().getSigla());
        Regiao newRegiao;
        if (optRegiao.isPresent()) {
            newRegiao = optRegiao.get();
        } else {
            newRegiao = this.regiaoService.save(estado.getRegiao());
        }
        estado.setRegiao(newRegiao);
        return this.repository.save(estado);
    }

    public List<Estado> getAll() {
        return this.repository.findAll();
    }
    
    public Optional<Estado> getOptionalBySigla(String sigla) {
        return this.repository.findById(sigla);
    }

    public List<Estado> getEstadosByRegiao(Regiao regiao) {
        return this.repository.findEstadosByRegiaoSigla(regiao.getSigla());
    }

    public Estado getBySiglaEstado(String siglaEstado) {
        Optional<Estado> estado = this.repository.findById(siglaEstado);
        if (estado.isPresent()) {
            return estado.get();
        } else {
            throw new ResourceNotFound("" + siglaEstado);
        }
    }

    public void deleteEstadoBySiglaEstado(String siglaEstado) {
        this.getBySiglaEstado(siglaEstado);
        this.repository.deleteById(siglaEstado);
    }

}
