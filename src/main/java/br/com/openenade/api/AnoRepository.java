package br.com.openenade.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnoRepository extends JpaRepository<Ano, String> {

    public Ano findByAno(Integer ano);

    public void deleteByAno(Integer ano);

}
    