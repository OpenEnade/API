package br.com.openenade.api;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.openenade.api.Ano;

public interface AnoRepository extends JpaRepository<Ano, String> {

    public Ano getElemmentByAno(Integer ano);

    public void deleteByAno(Integer ano);

}
