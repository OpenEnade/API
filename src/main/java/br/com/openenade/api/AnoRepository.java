package br.com.openenade.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.openenade.api.Ano;

public interface AnoRepository extends JpaRepository<Ano, String> {

    public Ano getElemmentByAno(Integer ano);

}