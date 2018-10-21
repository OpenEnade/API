package br.com.openenade.api.estado;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EstadoService {

    @Autowired
    private EstadoRepository repository;
    
    public void save(Estado estado) {
        this.repository.save(estado);
    }
    
    public List<Estado> getAll(){
        return this.repository.findAll();
    }
    
    public Optional<Estado> getBySiglaEstado(String siglaEstado){
        return this.repository.findById(siglaEstado);
    }
    
    public void deleteEstadoBySiglaEstado(String siglaEstado) {
        this.repository.deleteBySiglaEstado(siglaEstado);
    }
    
}
