package br.com.openenade.api.curso;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotBlank;
import br.com.openenade.api.modalidade.Modalidade;
import javax.persistence.IdClass;

@Entity
@IdClass(CursoId.class)
public class Curso {

    @NotBlank(message = "Curso 'nome' nao pode ser vazio")
    private String nome;

    @Id
    @Positive(message = "Curso 'codigoArea' precisa ser positivo")
    private Long codigoArea;

    @Id
    @Enumerated(EnumType.ORDINAL)
    private Modalidade modalidade;

    public Curso() {

    }

    public Curso(String nome, long codigoArea, Modalidade modalidade) {
        super();
        this.nome = nome;
        this.codigoArea = codigoArea;
        this.modalidade = modalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Long codigoArea) {
        this.codigoArea = codigoArea;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoArea == null) ? 0 : codigoArea.hashCode());
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
        Curso other = (Curso) obj;
        if (codigoArea == null) {
            if (other.codigoArea != null)
                return false;
        } else if (!codigoArea.equals(other.codigoArea))
            return false;
        if (modalidade != other.modalidade)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Curso [nome=" + nome + ", codigoArea=" + codigoArea + ", modalidade=" + modalidade
                + "]";
    }

}
