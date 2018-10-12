package br.com.openenade.api;

import org.springframework.data.annotation.Id;

public class Ano {

	@Id
    private Integer ano;

    public Ano(Integer ano) {

        this.ano = ano;
    }

    public Integer getAno() {
        return this.ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }


}