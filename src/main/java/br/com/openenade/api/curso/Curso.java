package br.com.openenade.api.curso;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotBlank;
import br.com.openenade.api.modalidade.Modalidade;

@Entity
public class Curso {

    @Id
    @NotNull(message = "'codigoCurso' não pode ser nulo.")
    private Long codigoCurso;

    @NotBlank(message = "'nome' não pode ser vazio.")
    private String nome;

    @NotNull(message = "'codigoArea' não pode ser nulo.")
    @Positive(message = "'codigoArea' precisa ser positivo.")
    private Long codigoArea;

    @NotNull(message = "Você precisa especificar uma 'modalidade' de ensino.")
    private Modalidade modalidade;

    public Curso() {

    }

    public Curso(String nome, long codigoArea, long codigoCurso, Modalidade modalidade) {
        super();
        this.nome = nome;
        this.codigoArea = codigoArea;
        this.codigoCurso = codigoCurso;
        this.modalidade = modalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Long codigoArea) {
        this.codigoArea = codigoArea;
    }

    public long getCodigoCurso() {
        return codigoCurso;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    @Override
    public String toString() {
        return "Curso [codigoCurso=" + codigoCurso + ", nome=" + nome + ", codigoArea=" + codigoArea
                + ", modalidade=" + modalidade + "]";
    }

}
