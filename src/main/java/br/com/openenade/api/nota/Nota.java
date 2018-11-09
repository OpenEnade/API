package br.com.openenade.api.nota;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.universidade.Universidade;

@Entity
public class Nota {

    @Id
    private Integer index;

    @ManyToOne
    private Ano ano;

    @ManyToOne
    private Curso curso;

    @ManyToOne
    private Universidade universidade;

    private Integer concluintesInscritos;

    private Integer concluintesParticipantes;

    private Double notaBrutaFG;

    private Double notaPadronizadaFG;

    private Double notaBrutaCE;

    private Double notaPadronizadaCE;

    private Double enadeContinuo;

    private Short enadeFaixa;

    public Nota() {

    }

    public Nota(Integer index, Ano ano, Curso curso, Universidade universidade,
            Integer concluintesInscritos, Integer concluintesParticipantes, Double notaBrutaFG,
            Double notaPadronizadaFG, Double notaBrutaCE, Double notaPadronizadaCE,
            Double enadeContinuo, Short enadeFaixa) {
        super();
        this.index = index;
        this.ano = ano;
        this.curso = curso;
        this.universidade = universidade;
        this.concluintesInscritos = concluintesInscritos;
        this.concluintesParticipantes = concluintesParticipantes;
        this.notaBrutaFG = notaBrutaFG;
        this.notaPadronizadaFG = notaPadronizadaFG;
        this.notaBrutaCE = notaBrutaCE;
        this.notaPadronizadaCE = notaPadronizadaCE;
        this.enadeContinuo = enadeContinuo;
        this.enadeFaixa = enadeFaixa;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public Integer getConcluintesInscritos() {
        return concluintesInscritos;
    }

    public void setConcluintesInscritos(Integer concluintesInscritos) {
        this.concluintesInscritos = concluintesInscritos;
    }

    public Integer getConcluintesParticipantes() {
        return concluintesParticipantes;
    }

    public void setConcluintesParticipantes(Integer concluintesParticipantes) {
        this.concluintesParticipantes = concluintesParticipantes;
    }

    public Double getNotaBrutaFG() {
        return notaBrutaFG;
    }

    public void setNotaBrutaFG(Double notaBrutaFG) {
        this.notaBrutaFG = notaBrutaFG;
    }

    public Double getNotaPadronizadaFG() {
        return notaPadronizadaFG;
    }

    public void setNotaPadronizadaFG(Double notaPadronizadaFG) {
        this.notaPadronizadaFG = notaPadronizadaFG;
    }

    public Double getNotaBrutaCE() {
        return notaBrutaCE;
    }

    public void setNotaBrutaCE(Double notaBrutaCE) {
        this.notaBrutaCE = notaBrutaCE;
    }

    public Double getNotaPadronizadaCE() {
        return notaPadronizadaCE;
    }

    public void setNotaPadronizadaCE(Double notaPadronizadaCE) {
        this.notaPadronizadaCE = notaPadronizadaCE;
    }

    public Double getEnadeContinuo() {
        return enadeContinuo;
    }

    public void setEnadeContinuo(Double enadeContinuo) {
        this.enadeContinuo = enadeContinuo;
    }

    public Short getEnadeFaixa() {
        return enadeFaixa;
    }

    public void setEnadeFaixa(Short enadeFaixa) {
        this.enadeFaixa = enadeFaixa;
    }

}
