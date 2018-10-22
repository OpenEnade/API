package br.com.openenade.api.estado;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import br.com.openenade.api.regiao.Regiao;

@Entity
public class Estado {

    @Id
    @NotBlank(message = "'siglaEstado' não pode ser vazio.")
    private String siglaEstado;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Regiao regiaoEstado;

    public Estado() {
        
    }
    
    public Estado(@NotBlank(message = "'siglaEstado' não pode ser vazio.") String siglaEstado,
            Regiao regiaoEstado) {
        super();
        this.siglaEstado = siglaEstado;
        this.regiaoEstado = regiaoEstado;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public Regiao getRegiaoEstado() {
        return regiaoEstado;
    }

    public void setRegiaoEstado(Regiao regiaoEstado) {
        this.regiaoEstado = regiaoEstado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((regiaoEstado == null) ? 0 : regiaoEstado.hashCode());
        result = prime * result + ((siglaEstado == null) ? 0 : siglaEstado.hashCode());
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
        if (regiaoEstado == null) {
            if (other.regiaoEstado != null)
                return false;
        } else if (!regiaoEstado.equals(other.regiaoEstado))
            return false;
        if (siglaEstado == null) {
            if (other.siglaEstado != null)
                return false;
        } else if (!siglaEstado.equals(other.siglaEstado))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Estado [siglaEstado=" + siglaEstado + ", regiaoEstado=" + regiaoEstado + "]";
    }
    
    

}