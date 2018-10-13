package br.com.openenade.api.curso;

import javax.persistence.Id;
import javax.persistence.Entity;
import br.com.openenade.api.modalidade.Modalidade;

@Entity
public class Curso {

    @Id
    private long codigoCurso;

    private String nome;
    private long codigoArea;
    private Modalidade modalidade;

    public Curso() {

    }

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

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    @Override
    public String toString() {
        return "Curso [codigoCurso=" + codigoCurso + ", nome=" + nome + ", codigoArea=" + codigoArea
                + ", modalidade=" + modalidade + "]";
    }

}
