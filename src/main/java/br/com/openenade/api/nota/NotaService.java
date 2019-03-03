package br.com.openenade.api.nota;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.ano.AnoRepository;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.curso.CursoId;
import br.com.openenade.api.curso.CursoRepository;
import br.com.openenade.api.exceptions.ResourceNotFound;
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

    public Nota addNota(Nota nota) {
        Ano ano = this.anoRepository.save(nota.getInfo().getAno());
        Curso curso = this.cursoRepository.save(nota.getInfo().getCurso());

        Universidade uni = nota.getInfo().getUniversidade();
        Optional<Universidade> optUni = this.universidadeService
                .getOptUniversidadeById(uni.getCodigoIES(), uni.getCampus().getCodigo());
        if (optUni.isPresent()) {
            uni = optUni.get();
        } else {
            uni = this.universidadeService.addUniversidade(uni);
        }

        nota.getInfo().setAno(ano);
        nota.getInfo().setCurso(curso);
        nota.getInfo().setUniversidade(uni);
        return this.notaRepository.save(nota);
    }

    public Nota getNotaById(NotaId id) {
        Optional<Nota> optNota = this.notaRepository.findById(id);
        if (optNota.isPresent()) {
            return optNota.get();
        } else {
            throw new ResourceNotFound("Cannot find Nota by Id " + id.toString());
        }
    }

    public void deleteNotaById(NotaId id) {
        this.getNotaById(id);
        this.notaRepository.deleteById(id);
    }

    public List<Nota> getAll() {
        return this.notaRepository.findAll();
    }

    public Nota getNota(NotaIdInterface id) {
        NotaId notaId = makeNotaIdFromInterface(id);
        return this.getNotaById(notaId);
    }

    public void deleteNota(NotaIdInterface id) {
        NotaId notaId = makeNotaIdFromInterface(id);

        this.deleteNotaById(notaId);
    }

    private NotaId makeNotaIdFromInterface(NotaIdInterface idInterface) {
        Optional<Ano> optAno = this.anoRepository.findById(idInterface.getAno());

        Optional<Universidade> optUniversidade = this.universidadeService.getOptUniversidadeById(
                idInterface.getCodigoIES(), idInterface.getCodigoMunicipio());
        CursoId cursoId = new CursoId(idInterface.getCodigoArea(),
                Modalidade.values()[idInterface.getModalidade()]);
        Optional<Curso> optCurso = this.cursoRepository.findById(cursoId);

        return new NotaId(optAno.get(), optCurso.get(), optUniversidade.get());
    }


    public List<Nota> filterByGenericAttribute(NotaFilterInterface nfi) {

        FilterBy filter = new FilterBy(this.getAll());

        return filter.filterByRegiao(nfi.getRegiao()).filterByEstado(nfi.getEstado())
                .filterByMunicipio(nfi.getMunicipio()).filterByCategAdmin(nfi.getCategoria())
                .filterByCodigoIES(nfi.getUniversidade()).filterByCodigoArea(nfi.getCodigoArea())
                .filterByModalidadeEnsino(nfi.getModalidade())
                .filterByIntervaloAno(nfi.getBeginAno(), nfi.getEndAno()).get();
    }

}
