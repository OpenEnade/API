package br.com.openenade.api.nota;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.universidade.Universidade;

@Entity
public class Nota {


    @EmbeddedId
    private NotaId id;

    private Integer concluintesInscritos;

    private Integer concluintesParticipantes;

    private Double notaBrutaFG;

    private Double notaPadronizadaFG;

    private Double notaBrutaCE;

    private Double notaPadronizadaCE;

    private Double enadeContinuo;

    private Integer enadeFaixa;

    public Nota() {

    }

    public Nota(Ano ano, Curso curso, Universidade universidade) {
        super();
        this.id = new NotaId(ano, curso, universidade);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Nota other = (Nota) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public NotaId getId() {
        return id;
    }

    public void setId(NotaId id) {
        this.id = id;
    }

    public Ano getAno() {
        return this.id.getAno();
    }

    public void setAno(Ano ano) {
        this.id.setAno(ano);
    }

    public Curso getCurso() {
        return this.id.getCurso();
    }

    public void setCurso(Curso curso) {
        this.id.setCurso(curso);
    }

    public Universidade getUniversidade() {
        return this.id.getUniversidade();
    }

    public void setUniversidade(Universidade universidade) {
        this.id.setUniversidade(universidade);
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

    public Integer getEnadeFaixa() {
        return enadeFaixa;
    }

    public void setEnadeFaixa(Integer enadeFaixa) {
        this.enadeFaixa = enadeFaixa;
    }

}
