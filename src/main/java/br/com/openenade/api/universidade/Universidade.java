package br.com.openenade.api.universidade;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.municipio.Municipio;

@Entity
public class Universidade {

    @Id
    @NotNull(message = "'codigoCurso' não pode ser nulo.")
    private Long codigoIES;

    @NotBlank(message = "'nome' não pode ser vazio.")
    private String nome;

    @ManyToOne(optional = false)
    @NotNull(message = "'campus' não pode ser nulo.")
    private Municipio campus;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Você precisa especificar uma 'categoriaAdmin'.")
    private CategoriaAdmin categoriaAdmin;

    @OneToMany
    @NotNull(message = "Você precisa especificar um 'curso'.")
    private List<Curso> curso;

    public Universidade() {
        this.curso = new ArrayList<>();
    }

    public Universidade(Long codigoIES, String nome, Municipio campus,
            CategoriaAdmin categoriaAdmin, List<Curso> curso) {
        super();
        this.codigoIES = codigoIES;
        this.nome = nome;
        this.campus = campus;
        this.categoriaAdmin = categoriaAdmin;
        this.curso = curso;
    }

    public Long getCodigoIES() {
        return codigoIES;
    }

    public void setCodigoIES(Long codigoIES) {
        this.codigoIES = codigoIES;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Municipio getCampus() {
        return campus;
    }

    public void setCampus(Municipio campus) {
        this.campus = campus;
    }

    public CategoriaAdmin getCategoriaAdmin() {
        return categoriaAdmin;
    }

    public void setCategoriaAdmin(CategoriaAdmin categoriaAdmin) {
        this.categoriaAdmin = categoriaAdmin;
    }

    public List<Curso> getCurso() {
        return curso;
    }

    public void setCurso(List<Curso> curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Universidade [codigoIES=" + codigoIES + ", nome=" + nome + ", campus=" + campus
                + ", categoriaAdmin=" + categoriaAdmin + ", curso=" + curso + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((campus == null) ? 0 : campus.hashCode());
        result = prime * result + ((categoriaAdmin == null) ? 0 : categoriaAdmin.hashCode());
        result = prime * result + ((codigoIES == null) ? 0 : codigoIES.hashCode());
        result = prime * result + ((curso == null) ? 0 : curso.hashCode());
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
        Universidade other = (Universidade) obj;
        if (categoriaAdmin != other.categoriaAdmin)
            return false;
        if (codigoIES == null) {
            if (other.codigoIES != null)
                return false;
        } else if (!codigoIES.equals(other.codigoIES))
            return false;
        return true;
    }


}