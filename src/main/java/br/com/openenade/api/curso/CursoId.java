package br.com.openenade.api.curso;

import java.io.Serializable;
import br.com.openenade.api.modalidade.Modalidade;

public class CursoId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long codigoCurso;

    private Modalidade modalidade;

    public CursoId() {

    }

    public CursoId(Long codigoCurso, Modalidade modalidade) {
        super();
        this.codigoCurso = codigoCurso;
        this.modalidade = modalidade;
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
