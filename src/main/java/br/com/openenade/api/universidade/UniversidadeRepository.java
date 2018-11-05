package br.com.openenade.api.universidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversidadeRepository extends JpaRepository<Universidade, Long> {

    public void deleteUniversidadesByCampusCodigo(Long codigo);
}
