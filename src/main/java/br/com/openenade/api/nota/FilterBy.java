package br.com.openenade.api.nota;

import java.util.List;
import java.util.stream.Collectors;
import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.modalidade.Modalidade;

public class FilterBy {

    private List<Nota> notas;

    public FilterBy(List<Nota> notas) {

        this.notas = notas;

    }

    public List<Nota> get() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public FilterBy filterByRegiao(String regiao) {

        if (regiao != null) {
            this.notas = this.notas
                    .stream().filter(nota -> nota.getInfo().getUniversidade().getCampus()
                            .getEstado().getRegiao().getSigla().equals(regiao))
                    .collect(Collectors.toList());
        }
        return this;
    }

    public FilterBy filterByEstado(String estado) {

        if (estado != null) {
            this.notas =
                    this.notas
                            .stream().filter(nota -> nota.getInfo().getUniversidade().getCampus()
                                    .getEstado().getSigla().equals(estado))
                            .collect(Collectors.toList());
        }
        return this;
    }

    public FilterBy filterByMunicipio(Long municipio) {

        if (municipio != null) {
            this.notas = this.notas.stream().filter(nota -> nota.getInfo().getUniversidade()
                    .getCampus().getCodigo().equals(municipio)).collect(Collectors.toList());
        }
        return this;
    }

    public FilterBy filterByCategAdmin(CategoriaAdmin catAdmin) {

        if (catAdmin != null) {
            this.notas = this.notas.stream().filter(
                    nota -> nota.getInfo().getUniversidade().getCategoriaAdmin().equals(catAdmin))
                    .collect(Collectors.toList());
        }
        return this;
    }

    public FilterBy filterByCodigoIES(Long codigoIES) {

        if (codigoIES != null) {
            this.notas = this.notas.stream().filter(
                    nota -> nota.getInfo().getUniversidade().getCodigoIES().equals(codigoIES))
                    .collect(Collectors.toList());
        }
        return this;
    }

    public FilterBy filterByCodigoCurso(Long codigoCurso) {

        if (codigoCurso != null) {
            this.notas = this.notas.stream()
                    .filter(nota -> nota.getInfo().getCurso().getCodigoCurso().equals(codigoCurso))
                    .collect(Collectors.toList());
        }
        return this;
    }

    public FilterBy filterByCodigoArea(Long codigoArea) {

        if (codigoArea != null) {
            this.notas = this.notas.stream()
                    .filter(nota -> nota.getInfo().getCurso().getCodigoArea().equals(codigoArea))
                    .collect(Collectors.toList());
        }
        return this;
    }

    public FilterBy filterByModalidadeEnsino(Modalidade modalidade) {

        if (modalidade != null) {
            this.notas = this.notas.stream()
                    .filter(nota -> nota.getInfo().getCurso().getModalidade().equals(modalidade))
                    .collect(Collectors.toList());
        }
        return this;
    }

    public FilterBy filterByIntervaloAno(Integer anoIni, Integer anoFin) {

        if (anoIni != null && anoFin != null) {
            this.notas = this.notas.stream()
                    .filter(nota -> (nota.getInfo().getAno().getAno() >= anoIni)
                            && (nota.getInfo().getAno().getAno() <= anoFin))
                    .collect(Collectors.toList());
        }
        return this;
    }
}
