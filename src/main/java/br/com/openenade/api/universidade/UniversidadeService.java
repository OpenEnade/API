package br.com.openenade.api.universidade;

import java.util.Collection;
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
        Optional<Municipio> optMunicipio =
                this.municipioService.getOptionalByCodigo(universidade.getCampus().getCodigo());
        Municipio newCampus;
        if (optMunicipio.isPresent()) {
            newCampus = optMunicipio.get();
        } else {
            newCampus = this.municipioService.save(universidade.getCampus());
        }
        universidade.setCampus(newCampus);
        return this.repository.save(universidade);

    }

    public Collection<Universidade> getAll() {
        return this.repository.findAll();
    }

    
    //Issue #38
    public Collection<Universidade> getAllByCodigoIES(Long codigoIES) {
    	
    	return this.repository.findAllByCodigoIES(codigoIES);
  
    }
    
    public Universidade getUniversidadeByCodigoIES(Long codigoIES) {
        Optional<Universidade> optUnivesidade = this.repository.findByCodigoIES(codigoIES);
        if (optUnivesidade.isPresent()) {
            return optUnivesidade.get();
        } else {
            throw new ResourceNotFound("" + codigoIES);
        }
    }

  
    public void deleteUniversidadeByCodigoIES(Long codigoIES) {
        this.repository.deleteByCodigoIES(codigoIES);
    }

    public void deleteUniversidadesByMunicipioCodigo(Municipio campus) {
        
        this.repository.deleteAllByCampus(campus);
    }
    
    public Optional<Universidade> getUniversidadeById(Long codigoIES, Municipio campus) {
    	
    	UniversidadeId id = new UniversidadeId(codigoIES, campus.getCodigo());
    	return this.repository.findById(id);
    	
    }
    
    
    public void deleteUniversidadeById(Long codigoIES, Municipio campus) {

    	UniversidadeId id = new UniversidadeId(codigoIES, campus.getCodigo());
    	this.repository.deleteById(id);
    }
    
}
