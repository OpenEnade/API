package br.com.openenade.api.nota;

public class NotaIdInterface {

    private int ano;
    private long codigoArea;
    private int modalidade;
    private long codigoIES;
    private long codigoMunicipio;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public long getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(long codigoArea) {
        this.codigoArea = codigoArea;
    }

    public int getModalidade() {
        return modalidade;
    }

    public void setModalidade(int modalidade) {
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
