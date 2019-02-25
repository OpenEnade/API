package br.com.openenade.api.curso;

import java.io.Serializable;
import br.com.openenade.api.modalidade.Modalidade;

public class CursoId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long codigoArea;

    private Modalidade modalidade;

    public CursoId() {

    }

    public CursoId(Long codigoArea, Modalidade modalidade) {
        super();
        this.codigoArea = codigoArea;
        this.modalidade = modalidade;
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

}
