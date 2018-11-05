package br.com.openenade.api.municipio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.estado.EstadoService;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.universidade.UniversidadeService;

@Service
@Transactional
public class MunicipioService {

    @Autowired
    private MunicipioRepository repository;
    
    @Autowired
    private EstadoService estadoService;
    
    @Autowired
    private UniversidadeService universidadeService;
    
    public Municipio save(Municipio municipio) {
        Optional<Estado> optEstado =
                this.estadoService.getOptionalBySigla(municipio.getEstado().getSigla());
        Estado newEstado;
        if (optEstado.isPresent()) {
            newEstado = optEstado.get();
        } else {
            newEstado = this.estadoService.save(municipio.getEstado());
        }
        municipio.setEstado(newEstado);
        return this.repository.save(municipio);
    }
    
    public List<Municipio> getAll(){
        return this.repository.findAll();
    }
    
    public Optional<Municipio> getOptionalByCodigo(Long codigo) {
        return this.repository.findById(codigo);
    }
    
    public Municipio getMunicipioByCodigo(Long codigo) {
        Optional<Municipio> optMunicipio = this.repository.findById(codigo);
        if (optMunicipio.isPresent()) {
            return optMunicipio.get();
        }else {
            throw new ResourceNotFound("" + codigo);
        }
    }
    
    public Municipio getByCodigo(Long codigo){
        return this.repository.findById(codigo).get();
    }
    
    public void deleteMunicipioByCodigo(Long codigo) {
        this.getMunicipioByCodigo(codigo);
        this.universidadeService.deleteUniversidadesByMunicipioCodigo(codigo);
        this.repository.deleteById(codigo);
    }

    public void deleteMunicipiosByEstadoSigla(String siglaEstado) {
        this.repository.deleteMunicipiosByEstadoSigla(siglaEstado);
        
    }
}
