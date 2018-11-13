package br.com.openenade.api.nota;

import java.io.Serializable;
import javax.persistence.Embeddable;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.universidade.Universidade;

@Embeddable
public class NotaId implements Serializable {

    private Ano ano;
    private Curso curso;
    private Universidade universidade;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public NotaId() {

    }

    public NotaId(Ano ano, Curso curso, Universidade universidade) {
        super();
        this.ano = ano;
        this.curso = curso;
        this.universidade = universidade;
    }

    public Ano getAno() {
        return ano;
    }

    public void setAno(Ano ano) {
        this.ano = ano;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Universidade getUniversidade() {
        return universidade;
    }

    public void setUniversidade(Universidade universidade) {
        this.universidade = universidade;
    }

}
