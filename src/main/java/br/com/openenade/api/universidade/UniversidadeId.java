package br.com.openenade.api.universidade;

import java.io.Serializable;

public class UniversidadeId implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long codigoIES;
    private Long campus;

    public UniversidadeId() {};

    public Long getCodigoIES() {
        return codigoIES;
    }

    public void setCodigoIES(Long codigoIES) {
        this.codigoIES = codigoIES;
    }

    public Long getCampus() {
        return campus;
    }

    public void setCampus(Long campus) {
        this.campus = campus;
    }

    public UniversidadeId(Long codigoIES, Long campus) {
        super();
        this.codigoIES = codigoIES;
        this.campus = campus;
    }

}
