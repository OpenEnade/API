package br.com.openenade.api.nota;

import br.com.openenade.api.modalidade.Modalidade;

public class NotaIdInterface {

    private int ano;
    private long codigoCurso;
    private Modalidade modalidade;
    private long codigoIES;
    private long codigoMunicipio;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public long getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(long codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public long getCodigoIES() {
        return codigoIES;
    }

    public void setCodigoIES(long codigoIES) {
        this.codigoIES = codigoIES;
    }

    public long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

}
