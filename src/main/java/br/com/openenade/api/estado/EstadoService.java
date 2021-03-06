package br.com.openenade.api.estado;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.municipio.MunicipioService;
import br.com.openenade.api.regiao.Regiao;
import br.com.openenade.api.regiao.RegiaoService;

@Service
@Transactional
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private RegiaoService regiaoService;

    @Autowired
    private MunicipioService municipioService;

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

    public Estado getBySiglaEstado(String siglaEstado) {
        Optional<Estado> estado = this.repository.findById(siglaEstado);
        if (estado.isPresent()) {
            return estado.get();
        } else {
            throw new ResourceNotFound("" + siglaEstado);
        }
    }

    public void deleteEstadoById(String siglaEstado) {
        this.getBySiglaEstado(siglaEstado);
        this.municipioService.deleteMunicipiosByEstadoSigla(siglaEstado);
        this.repository.deleteById(siglaEstado);
    }

    public void deleteEstadosByRegiaoSigla(String regiaoSigla) {
        for (Estado estado : this.repository.findEstadosByRegiaoSigla(regiaoSigla)) {
            this.deleteEstadoById(estado.getSigla());
        }
    }

}
