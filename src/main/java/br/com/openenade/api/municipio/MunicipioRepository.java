package br.com.openenade.api.municipio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long>{

    public List<Municipio> findMunicipioByCodigo(Long codigo);
}
