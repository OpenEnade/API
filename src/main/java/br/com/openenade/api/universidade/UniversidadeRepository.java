package br.com.openenade.api.universidade;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversidadeRepository extends JpaRepository<Universidade, Long> {

    public List<Universidade> findUniversidadeByCodigoIES(Long codigo);
}
