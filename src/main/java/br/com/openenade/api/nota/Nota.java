package br.com.openenade.api.nota;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.universidade.Universidade;

@Entity
public class Nota {

    private Avaliacao avaliacao;

    @EmbeddedId
    private NotaId info;
    
    public static class Builder {

        private NotaId info;
        
        public Builder() {
            this.info = new NotaId();
        }

        public Builder setAno(Ano ano) {
            this.info.setAno(ano);

            return this;
        }

        public Builder setCurso(Curso curso) {
            this.info.setCurso(curso);

            return this;
        }

        public Builder setUniversidade(Universidade universidade) {
            this.info.setUniversidade(universidade);

            return this;
        }

        public Nota build() {
            Nota nota = new Nota();
            nota.setInfo(info);

            return nota;
        }

    }

    public Nota() {
        this.setAvaliacao(new Avaliacao());
    }

    @Override
    public String toString () {
        
        return this.info.toString();
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((info == null) ? 0 : info.hashCode());
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
        if (info == null) {
            if (other.info != null)
                return false;
        } else if (!info.equals(other.info))
            return false;
        return true;
    }

    public NotaId getInfo() {
        return info;
    }

    public void setInfo(NotaId info) {
        this.info = info;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

}
