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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + concluintesInscritos;
        result = prime * result + concluintesParticipantes;
        long temp;
        temp = Double.doubleToLongBits(enadeContinuo);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + enadeFaixa;
        temp = Double.doubleToLongBits(notaBrutaCE);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(notaBrutaFG);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(notaPadronizadaCE);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(notaPadronizadaFG);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        Avaliacao other = (Avaliacao) obj;

        return concluintesInscritos == other.concluintesInscritos
                && concluintesParticipantes == other.concluintesParticipantes
                && Double.doubleToLongBits(enadeContinuo) == Double
                        .doubleToLongBits(other.enadeContinuo)
                && enadeFaixa == other.enadeFaixa
                && Double.doubleToLongBits(notaBrutaCE) == Double
                        .doubleToLongBits(other.notaBrutaCE)
                && Double.doubleToLongBits(notaBrutaFG) == Double
                        .doubleToLongBits(other.notaBrutaFG)
                && Double.doubleToLongBits(notaPadronizadaCE) == Double
                        .doubleToLongBits(other.notaPadronizadaCE)
                && Double.doubleToLongBits(notaPadronizadaFG) == Double
                        .doubleToLongBits(other.notaPadronizadaFG);
    }

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
