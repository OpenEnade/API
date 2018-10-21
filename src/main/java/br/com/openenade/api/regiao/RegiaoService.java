package br.com.openenade.api.regiao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegiaoService {
    
    @Autowired
    private RegiaoRepository repository;
    
    public void save(Regiao regiao) {
        this.repository.save(regiao);
    }
    
    public List<Regiao> getAll(){
        return this.repository.findAll();
    }
    
    public Optional<Regiao> getBySigla(String sigla){
        return this.repository.findById(sigla);
    }
    
    public void deleteRegiaoBySigla(String sigla) {
        this.repository.deleteBySigla(sigla);
    }
}
