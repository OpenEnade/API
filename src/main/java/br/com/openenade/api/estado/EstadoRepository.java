package br.com.openenade.api.estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, String> {

    public Estado findBySigla(String sigla);

    public void deleteBySigla(String sigla);

}
    