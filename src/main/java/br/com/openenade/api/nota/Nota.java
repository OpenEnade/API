package br.com.openenade.api.nota;

import javax.persistence.Entity;
import javax.persistence.Id;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.universidade.Universidade;

@Entity
public class Nota {

    @Id
    private Integer index;

    private Ano ano;

    private Curso curso;

    private Universidade universidade;

    private Integer concluintesInscritos;

    private Integer concluintesParticipantes;

    private Double notaBrutaFG;

    private Double notaPadronizadaFG;

    private Double notaBrutaCE;

    private Double notaPadronizadaCE;

    private Double enadeContinuo;

    private Short enadeFaixa;

    public Nota() {

    }

    public Nota(Integer index, Ano ano, Curso curso, Universidade universidade,
            Integer concluintesInscritos, Integer concluintesParticipantes, Double notaBrutaFG,
            Double notaPadronizadaFG, Double notaBrutaCE, Double notaPadronizadaCE,
            Double enadeContinuo, Short enadeFaixa) {
        super();
        this.index = index;
        this.ano = ano;
        this.curso = curso;
        this.universidade = universidade;
        this.concluintesInscritos = concluintesInscritos;
        this.concluintesParticipantes = concluintesParticipantes;
        this.notaBrutaFG = notaBrutaFG;
        this.notaPadronizadaFG = notaPadronizadaFG;
        this.notaBrutaCE = notaBrutaCE;
        this.notaPadronizadaCE = notaPadronizadaCE;
        this.enadeContinuo = enadeContinuo;
        this.enadeFaixa = enadeFaixa;
    }

}
