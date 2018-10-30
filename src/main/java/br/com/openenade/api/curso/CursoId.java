package br.com.openenade.api.curso;

import java.io.Serializable;
import br.com.openenade.api.modalidade.Modalidade;

public class CursoId implements Serializable {

    private Long codigoCurso;

    private Modalidade modalidade;

    public CursoId() {

    }

    public CursoId(Long codigoCurso, Modalidade modalidade) {
        super();
        this.codigoCurso = codigoCurso;
        this.modalidade = modalidade;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoCurso == null) ? 0 : codigoCurso.hashCode());
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
        CursoId other = (CursoId) obj;
        if (codigoCurso == null) {
            if (other.codigoCurso != null)
                return false;
        } else if (!codigoCurso.equals(other.codigoCurso))
            return false;
        if (modalidade != other.modalidade)
            return false;
        return true;
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

}
