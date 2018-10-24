package br.com.openenade.api.estado;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.openenade.api.regiao.Regiao;

@Entity
public class Estado {

    @Id
    @NotBlank(message = "'siglaEstado' não pode ser vazio.")
    private String sigla;

    @ManyToOne(optional = false)
    @NotNull(message = "'regiaoEstado' não pode ser nulo.")
    private Regiao regiao;

    public Estado() {

    }

    public Estado(@NotBlank(message = "'siglaEstado' não pode ser vazio.") String siglaEstado,
            Regiao regiaoEstado) {
        super();
        this.sigla = siglaEstado;
        this.regiao = regiaoEstado;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String siglaEstado) {
        this.sigla = siglaEstado;
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
        result = prime * result + ((regiao == null) ? 0 : regiao.hashCode());
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
        if (regiao == null) {
            if (other.regiao != null)
                return false;
        } else if (!regiao.equals(other.regiao))
            return false;
        if (sigla == null) {
            if (other.sigla != null)
                return false;
        } else if (!sigla.equals(other.sigla))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Estado [siglaEstado=" + sigla + ", regiaoEstado=" + regiao + "]";
    }



}
