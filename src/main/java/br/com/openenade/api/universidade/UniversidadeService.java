package br.com.openenade.api.universidade;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.municipio.MunicipioService;

@Service
@Transactional
public class UniversidadeService {

    @Autowired
    private UniversidadeRepository repository;
    
    @Autowired
    private MunicipioService municipioService;
    
    public Universidade save(Universidade universidade) {
        Optional<Municipio> optMunicipio = this.municipioService.getOptionalByCodigo
                (universidade.getCampus().getCodigo());
        Municipio newCampus;
        if(optMunicipio.isPresent()) {
            newCampus = optMunicipio.get();
        } else {
            newCampus = this.municipioService.save(universidade.getCampus());
        }
        universidade.setCampus(newCampus);
        return this.repository.save(universidade);
        
    }
    
    public List<Universidade> getAll(){
        return this.repository.findAll();
    }
    
    public Optional<Universidade> getOptionalUniversidadeByCodigoIES(Long codigoIES){
        return this.repository.findById(codigoIES);
    }
    
    public Universidade getUniversidadeByCodigoIES(Long codigoIES) {
        Optional<Universidade> optUnivesidade = this.repository.findById(codigoIES);
        if(optUnivesidade.isPresent()) {
            return optUnivesidade.get();
        } else {
            throw new ResourceNotFound("" + codigoIES);
        }
    }
    
    public List<Universidade> getByCodigoIES(Long codigoIES){
        return this.repository.findUniversidadeByCodigoIES(codigoIES);
    }
    
    public void deleteUniversidadeByCodigoIES(Long codigoIES) {
        this.getUniversidadeByCodigoIES(codigoIES);
        this.repository.deleteById(codigoIES);
    }
}
