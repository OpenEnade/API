package br.com.openenade.api.nota;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import br.com.openenade.api.ano.Ano;

@Embeddable
public class NotaId {

    @NotNull
    private Ano ano;

    @NotNull
    private Integer index;

    public NotaId() {

    }

    public NotaId(@NotNull Ano ano, @NotNull Integer index) {
        super();
        this.ano = ano;
        this.index = index;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ano == null) ? 0 : ano.hashCode());
        result = prime * result + ((index == null) ? 0 : index.hashCode());
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
        if (index == null) {
            if (other.index != null)
                return false;
        } else if (!index.equals(other.index))
            return false;
        return true;
    }

    public Ano getAno() {
        return ano;
    }

    public void setAno(Ano ano) {
        this.ano = ano;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

}
