package br.com.openenade.api.estado;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import br.com.openenade.api.regiao.Regiao;

@Entity
public class Estado {

    @Id
    private String sigla;

    @ManyToOne
    private Regiao regiao;


    @Override
    public String toString() {
        return "Estado [sigla=" + sigla + ", regiao=" + regiao + "]";
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
        Estado other = (Estado) obj;
        if (sigla == null) {
            if (other.sigla != null)
                return false;
        } else if (!sigla.equals(other.sigla))
            return false;
        return true;
    }
    
}
