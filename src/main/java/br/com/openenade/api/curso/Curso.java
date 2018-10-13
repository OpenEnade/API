package br.com.openenade.api.curso;

import br.com.openenade.api.modalidade.Modalidade;

public class Curso {

    private String nome;
    private long codigoArea;
    private long codigoCurso;
    private Modalidade modalidade;
    
    public Curso(String nome, long codigoArea, long codigoCurso, Modalidade modalidade) {
        super();
        this.nome = nome;
        this.codigoArea = codigoArea;
        this.codigoCurso = codigoCurso;
        this.modalidade = modalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(long codigoArea) {
        this.codigoArea = codigoArea;
    }

    public long getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(long codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

}
