package br.com.openenade.api.nota;

import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.modalidade.Modalidade;

public class NotaFilterInterface {

    private Integer beginAno;
    private Integer endAno;
    private CategoriaAdmin categoria;
    private Long codigoArea;
    private String estado;
    private Modalidade modalidade;
    private Long municipio;
    private String regiao;
    private Long universidade;

    public Integer getBeginAno() {
        return beginAno;
    }

    public void setBeginAno(Integer beginAno) {
        this.beginAno = beginAno;
    }

    public Integer getEndAno() {
        return endAno;
    }

    public void setEndAno(Integer endAno) {
        this.endAno = endAno;
    }

    public CategoriaAdmin getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAdmin categoria) {
        this.categoria = categoria;
    }

    public Long getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Long codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Modalidade getModalidade() {
        return modalidade;
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
    }

    public Long getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Long municipio) {
        this.municipio = municipio;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Long getUniversidade() {
        return universidade;
    }

    public void setUniversidade(Long universidade) {
        this.universidade = universidade;
    }

}
