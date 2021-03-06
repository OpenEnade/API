package br.com.openenade.api.estado;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, String> {

    public List<Estado> findEstadosByRegiaoSigla(String regiaoSigla);
    
}
