package br.com.openenade.api.ano;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnoRepository extends JpaRepository<Ano, String> {

    public Ano findByAno(Integer ano);

    public void deleteByAno(Integer ano);

}
    