package br.com.openenade.api.municipio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.estado.EstadoService;
import br.com.openenade.api.exceptions.ResourceNotFound;

@Service
@Transactional
public class MunicipioService {

    @Autowired
    private MunicipioRepository repository;
    
    @Autowired
    private EstadoService estadoService;
    
    public void save(Municipio municipio) {
        Optional<Estado> optEstado =
                this.estadoService.getOptionalBySigla(municipio.getEstado().getSigla());
        Estado newEstado;
        if (optEstado.isPresent()) {
            newEstado = optEstado.get();
        } else {
            newEstado = this.estadoService.save(municipio.getEstado());
        }
        municipio.setEstado(newEstado);
        this.repository.save(municipio);
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
    
    public List<Municipio> getByCodigo(Long codigo){
        return this.repository.findMunicipioByCodigo(codigo);
    }
    
    public void deleteMunicipioByCodigo(Long codigo) {
        this.getMunicipioByCodigo(codigo);
        this.repository.deleteById(codigo);
    }
}
