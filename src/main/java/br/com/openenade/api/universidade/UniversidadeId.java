package br.com.openenade.api.universidade;

import java.io.Serializable;


public class UniversidadeId implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long codigoIES;
    private Long campus;

    public UniversidadeId() {};

	public UniversidadeId(Long codigoIES, Long campus) {
		super();
		this.codigoIES = codigoIES;
		this.campus = campus;
	}

}
