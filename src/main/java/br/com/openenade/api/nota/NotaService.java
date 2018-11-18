package br.com.openenade.api.nota;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.ano.AnoRepository;
import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.curso.CursoId;
import br.com.openenade.api.curso.CursoRepository;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.modalidade.Modalidade;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.regiao.Regiao;
import br.com.openenade.api.universidade.Universidade;
import br.com.openenade.api.universidade.UniversidadeService;

@Service
@Transactional
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AnoRepository anoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UniversidadeService universidadeService;

    public Nota save(Nota nota) {
        this.anoRepository.save(nota.getInfo().getAno());

        this.cursoRepository.save(nota.getInfo().getCurso());

        Universidade universidadeA = nota.getInfo().getUniversidade();

        Optional<Universidade> optUniB = this.universidadeService.getUniversidadeById(
                universidadeA.getCodigoIES(), universidadeA.getCampus().getCodigo());

        if (optUniB.isPresent()) {
            Universidade universidadeB = optUniB.get();

            universidadeA.getCursos().addAll(universidadeB.getCursos());
        }

        this.universidadeService.save(universidadeA);

        return this.notaRepository.save(nota);
    }

    public Optional<Nota> getNotaById(NotaId id) {
        return this.notaRepository.findById(id);
    }

    public void deleteNotaById(NotaId id) {
        this.notaRepository.deleteById(id);
    }

    public List<Nota> getAll() {
        return this.notaRepository.findAll();
    }

    public Optional<Nota> getNota(NotaIdInterface id) {
        NotaId notaId = makeNotaIdFromInterface(id);

        return this.notaRepository.findById(notaId);
    }

    public void deleteNota(NotaIdInterface id) {
        NotaId notaId = makeNotaIdFromInterface(id);

        this.notaRepository.deleteById(notaId);
    }

    private NotaId makeNotaIdFromInterface(NotaIdInterface idInterface) {
        Optional<Ano> optAno = this.anoRepository.findById(idInterface.getAno());

        CursoId cursoId = new CursoId(idInterface.getCodigoCurso(), idInterface.getModalidade());
        Optional<Curso> optCurso = this.cursoRepository.findById(cursoId);

        Optional<Universidade> optUniversidade = this.universidadeService
                .getUniversidadeById(idInterface.getCodigoIES(), idInterface.getCodigoMunicipio());

        return new NotaId(optAno.get(), optCurso.get(), optUniversidade.get());
    }


    private Collection<Nota> filterByRegiao(Collection<Nota> col, Regiao regiao) {

        return col.stream().filter(nota -> nota.getInfo().getUniversidade().getCampus().getEstado()
                .getRegiao().equals(regiao)).collect(Collectors.toList());
    }

    private Collection<Nota> filterByEstado(Collection<Nota> col, Estado estado) {

        return col.stream().filter(
                nota -> nota.getInfo().getUniversidade().getCampus().getEstado().equals(estado))
                .collect(Collectors.toList());
    }

    private Collection<Nota> filterByMunicipio(Collection<Nota> col, Municipio municipio) {

        return col.stream()
                .filter(nota -> nota.getInfo().getUniversidade().getCampus().equals(municipio))
                .collect(Collectors.toList());
    }

    private Collection<Nota> filterByCategAdmin(Collection<Nota> col, CategoriaAdmin catAdmin) {

        return col.stream().filter(
                nota -> nota.getInfo().getUniversidade().getCategoriaAdmin().equals(catAdmin))
                .collect(Collectors.toList());
    }

    private Collection<Nota> filterByUniversidade(Collection<Nota> col, Universidade uni) {

        return col.stream().filter(nota -> nota.getInfo().getUniversidade().equals(uni))
                .collect(Collectors.toList());
    }

    private Collection<Nota> filterByCurso(Collection<Nota> col, Curso curso) {

        return col.stream().filter(nota -> nota.getInfo().getCurso().equals(curso))
                .collect(Collectors.toList());
    }

    private Collection<Nota> filterByModalidadeEnsino(Collection<Nota> col, Modalidade modalidade) {

        return col.stream()
                .filter(nota -> nota.getInfo().getCurso().getModalidade().equals(modalidade))
                .collect(Collectors.toList());
    }

    private Collection<Nota> filterByAno(Collection<Nota> col, Ano ano) {

        return col.stream().filter(nota -> nota.getInfo().getAno().equals(ano))
                .collect(Collectors.toList());
    }


    private Collection<Nota> filterByIntervaloAno(Collection<Nota> col, Ano anoIni, Ano anoFin) {

        return col.stream()
                .filter(nota -> (nota.getInfo().getAno().getAno() >= anoIni.getAno())
                        && (nota.getInfo().getAno().getAno() <= anoFin.getAno()))
                .collect(Collectors.toList());
    }


    public Collection<Nota> filterBy(Regiao regiao, Estado estado, Municipio municipio,
            CategoriaAdmin categoriaAdm, Universidade universidade, Curso curso,
            Modalidade modalidade, Ano ano) {

        Collection<Nota> atual = this.getAll();

        if (regiao != null) {

            atual = this.filterByRegiao(atual, regiao);
        }
        if (estado != null) {

            atual = this.filterByEstado(atual, estado);
        }
        if (municipio != null) {

            atual = this.filterByMunicipio(atual, municipio);
        }
        if (categoriaAdm != null) {

            atual = this.filterByCategAdmin(atual, categoriaAdm);
        }
        if (universidade != null) {

            atual = this.filterByUniversidade(atual, universidade);
        }
        if (curso != null) {

            atual = this.filterByCurso(atual, curso);
        }
        if (modalidade != null) {

            atual = this.filterByModalidadeEnsino(atual, modalidade);
        }
        if (ano != null) {

            atual = this.filterByAno(atual, ano);
        }

        return atual;



    }


}
