package br.com.openenade.api.nota;

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
import br.com.openenade.api.modalidade.Modalidade;
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

    private List<Nota> filterByRegiao(List<Nota> notas, String regiao) {

        if (regiao != null) {
            return notas
                    .stream().filter(nota -> nota.getInfo().getUniversidade().getCampus()
                            .getEstado().getRegiao().getSigla().equals(regiao))
                    .collect(Collectors.toList());
        } else {
            return notas;
        }
    }

    private List<Nota> filterByEstado(List<Nota> notas, String estado) {

        if (estado != null) {

            return notas.stream().filter(nota -> nota.getInfo().getUniversidade().getCampus()
                    .getEstado().getSigla().equals(estado)).collect(Collectors.toList());
        } else {
            return notas;
        }
    }

    private List<Nota> filterByMunicipio(List<Nota> notas, Long municipio) {

        if (municipio != null) {
            return notas.stream().filter(nota -> nota.getInfo().getUniversidade().getCampus()
                    .getCodigo().equals(municipio)).collect(Collectors.toList());
        } else {

            return notas;
        }
    }

    private List<Nota> filterByCategAdmin(List<Nota> notas, CategoriaAdmin catAdmin) {

        if (catAdmin != null) {
            return notas.stream().filter(
                    nota -> nota.getInfo().getUniversidade().getCategoriaAdmin().equals(catAdmin))
                    .collect(Collectors.toList());
        } else {
            return notas;
        }
    }

    private List<Nota> filterByCodigoIES(List<Nota> notas, Long codigoIES) {

        if (codigoIES != null) {
            return notas.stream().filter(
                    nota -> (nota.getInfo().getUniversidade().getCodigoIES().equals(codigoIES)))
                    .collect(Collectors.toList());
        } else {
            return notas;
        }
    }

    private List<Nota> filterByCodigoCurso(List<Nota> notas, Long codigoCurso) {

        if (codigoCurso != null) {
            return notas.stream().filter(
                    nota -> (nota.getInfo().getCurso().getCodigoCurso().equals(codigoCurso)))
                    .collect(Collectors.toList());
        } else {
            return notas;
        }
    }

    private List<Nota> filterByModalidadeEnsino(List<Nota> notas, Modalidade modalidade) {

        if (modalidade != null) {
            return notas.stream()
                    .filter(nota -> nota.getInfo().getCurso().getModalidade().equals(modalidade))
                    .collect(Collectors.toList());
        } else {
            return notas;
        }
    }

    private List<Nota> filterByAno(List<Nota> notas, Integer ano) {

        if (ano != null) {
            return notas.stream().filter(nota -> nota.getInfo().getAno().getAno().equals(ano))
                    .collect(Collectors.toList());
        } else {
            return notas;
        }
    }

//    Falta implementar no controller e fazer testes
//    private List<Nota> filterByIntervaloAno(List<Nota> col, Integer anoIni, Integer anoFin) {
//
//        return col.stream()
//                .filter(nota -> (nota.getInfo().getAno().getAno() >= anoIni)
//                        && (nota.getInfo().getAno().getAno() <= anoFin))
//                .collect(Collectors.toList());
//    }

    public List<Nota> filterByGenericAtribute(Integer ano, CategoriaAdmin catAdm, Long codigoCurso,
            String estado, Modalidade modalidade, Long municipio, String regiao, Long codigoIES) {

        List<Nota> notas = this.getAll();

        notas = filterByRegiao(notas, regiao);
        notas = filterByEstado(notas, estado);
        notas = filterByMunicipio(notas, municipio);
        notas = filterByCategAdmin(notas, catAdm);
        notas = filterByCodigoIES(notas, codigoIES);
        notas = filterByCodigoCurso(notas, codigoCurso);
        notas = filterByModalidadeEnsino(notas, modalidade);
        return filterByAno(notas, ano);

    }

    public void deleteAll() {

        this.notaRepository.deleteAll();

    }
}
