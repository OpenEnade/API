package br.com.openenade.api.nota;

import java.io.Serializable;
import javax.persistence.Embeddable;
import br.com.openenade.api.modalidade.Modalidade;

@Embeddable
public class NotaId implements Serializable {

    private int ano;
    private long codigoCurso;
    private Modalidade modalidade;
    private long codigoIES;
    private long codigoMunicipio;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public NotaId() {

    }

    public NotaId(int ano, long codigoCurso, Modalidade modalidade, long codigoIES,
            long codigoMunicipio) {

        this.ano = ano;
        this.codigoCurso = codigoCurso;
        this.modalidade = modalidade;
        this.codigoIES = codigoIES;
        this.codigoMunicipio = codigoMunicipio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ano;
        result = prime * result + (int) (codigoCurso ^ (codigoCurso >>> 32));
        result = prime * result + (int) (codigoIES ^ (codigoIES >>> 32));
        result = prime * result + (int) (codigoMunicipio ^ (codigoMunicipio >>> 32));
        result = prime * result + ((modalidade == null) ? 0 : modalidade.hashCode());
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
        NotaId other = (NotaId) obj;
        if (ano != other.ano)
            return false;
        if (codigoCurso != other.codigoCurso)
            return false;
        if (codigoIES != other.codigoIES)
            return false;
        if (codigoMunicipio != other.codigoMunicipio)
            return false;
        if (modalidade != other.modalidade)
            return false;
        return true;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Long getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(Long codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public Long getCodigoIES() {
        return codigoIES;
    }

    public void setCodigoIES(Long codigoIES) {
        this.codigoIES = codigoIES;
    }

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    @Override
    public String toString() {
        return "NotaId [ano=" + this.ano + ", codigoCurso=" + this.codigoCurso + ", modalidade="
                + this.modalidade + ", codigoIES=" + this.codigoIES + ", codigoMunicipio="
                + this.codigoMunicipio + "]";
    }

}
