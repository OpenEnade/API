package br.com.openenade.api.nota;

import java.io.Serializable;

public class Avaliacao implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private int concluintesInscritos;
    private int concluintesParticipantes;
    private double notaBrutaFG;
    private double notaPadronizadaFG;
    private double notaBrutaCE;
    private double notaPadronizadaCE;
    private double enadeContinuo;
    private int enadeFaixa;

    public int getConcluintesInscritos() {
        return concluintesInscritos;
    }

    public void setConcluintesInscritos(int concluintesInscritos) {
        this.concluintesInscritos = concluintesInscritos;
    }

    public int getConcluintesParticipantes() {
        return concluintesParticipantes;
    }

    public void setConcluintesParticipantes(int concluintesParticipantes) {
        this.concluintesParticipantes = concluintesParticipantes;
    }

    public double getNotaBrutaFG() {
        return notaBrutaFG;
    }

    public void setNotaBrutaFG(double notaBrutaFG) {
        this.notaBrutaFG = notaBrutaFG;
    }

    public double getNotaPadronizadaFG() {
        return notaPadronizadaFG;
    }

    public void setNotaPadronizadaFG(double notaPadronizadaFG) {
        this.notaPadronizadaFG = notaPadronizadaFG;
    }

    public double getNotaBrutaCE() {
        return notaBrutaCE;
    }

    public void setNotaBrutaCE(double notaBrutaCE) {
        this.notaBrutaCE = notaBrutaCE;
    }

    public double getNotaPadronizadaCE() {
        return notaPadronizadaCE;
    }

    public void setNotaPadronizadaCE(double notaPadronizadaCE) {
        this.notaPadronizadaCE = notaPadronizadaCE;
    }

    public double getEnadeContinuo() {
        return enadeContinuo;
    }

    public void setEnadeContinuo(double enadeContinuo) {
        this.enadeContinuo = enadeContinuo;
    }

    public int getEnadeFaixa() {
        return enadeFaixa;
    }

    public void setEnadeFaixa(int enadeFaixa) {
        this.enadeFaixa = enadeFaixa;
    }

}
