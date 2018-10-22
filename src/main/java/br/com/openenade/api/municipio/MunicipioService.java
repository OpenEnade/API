package br.com.openenade.api.municipio;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MunicipioService {

    @Autowired
    private MunicipioRepository repository;
    
    public void save(Municipio municipio) {
        this.repository.save(municipio);
    }
    
    public List<Municipio> getAll(){
        return this.repository.findAll();
    }
    
    public Optional<Municipio> getByCodigo(Long codigo){
        return this.repository.findById(codigo);
    }
    
    public void deleteMunicipioByCodigo(Long codigo) {
        this.repository.deleteByCodigo(codigo);
    }
}
