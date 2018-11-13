package br.com.openenade.api.nota;

import java.io.Serializable;
import javax.persistence.Embeddable;
import br.com.openenade.api.modalidade.Modalidade;

@Embeddable
public class NotaId implements Serializable {

    private Integer ano;
    private Long codigoCurso;
    private Modalidade modalidade;
    private Long codigoIES;
    private Long codigoMunicipio;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public NotaId() {

    }

    public NotaId(Integer ano, Long codigoCurso, Modalidade modalidade, Long codigoIES,
            Long codigoMunicipio) {

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
        result = prime * result + ((ano == null) ? 0 : ano.hashCode());
        result = prime * result + ((codigoCurso == null) ? 0 : codigoCurso.hashCode());
        result = prime * result + ((codigoIES == null) ? 0 : codigoIES.hashCode());
        result = prime * result + ((codigoMunicipio == null) ? 0 : codigoMunicipio.hashCode());
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
        if (ano == null) {
            if (other.ano != null)
                return false;
        } else if (!ano.equals(other.ano))
            return false;
        if (codigoCurso == null) {
            if (other.codigoCurso != null)
                return false;
        } else if (!codigoCurso.equals(other.codigoCurso))
            return false;
        if (codigoIES == null) {
            if (other.codigoIES != null)
                return false;
        } else if (!codigoIES.equals(other.codigoIES))
            return false;
        if (codigoMunicipio == null) {
            if (other.codigoMunicipio != null)
                return false;
        } else if (!codigoMunicipio.equals(other.codigoMunicipio))
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
