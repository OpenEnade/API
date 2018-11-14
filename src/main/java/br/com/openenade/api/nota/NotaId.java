package br.com.openenade.api.nota;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.universidade.Universidade;

@Embeddable
public class NotaId implements Serializable {

	@ManyToOne
	private Ano ano;

	@ManyToOne
	private Curso curso;

	@ManyToOne
	private Universidade universidade;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotaId() {

	}

	public NotaId(Ano ano, Curso curso, Universidade universidade) {
		super();
		this.ano = ano;
		this.curso = curso;
		this.universidade = universidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((universidade == null) ? 0 : universidade.hashCode());
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

		boolean anoCond = ano == null && other.ano == null || ano.equals(other.ano);
		boolean cursoCond = curso == null && other.curso == null || curso.equals(other.curso);
		boolean universidadeCond = universidade == null && other.universidade == null
				|| universidade.equals(universidade);

		return anoCond && cursoCond && universidadeCond;
	}

	public Ano getAno() {
		return ano;
	}

	public void setAno(Ano ano) {
		this.ano = ano;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Universidade getUniversidade() {
		return universidade;
	}

	public void setUniversidade(Universidade universidade) {
		this.universidade = universidade;
	}

	@Override
	public String toString() {
		return "NotaId [" + "ano=" + this.ano + ", curso=" + this.curso + ", universidade=" + this.universidade + "]";
	}

}
