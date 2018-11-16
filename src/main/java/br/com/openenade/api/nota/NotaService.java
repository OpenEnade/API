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
import br.com.openenade.api.universidade.Universidade;
import br.com.openenade.api.universidade.UniversidadeId;
import br.com.openenade.api.universidade.UniversidadeRepository;

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
    private UniversidadeRepository universidadeRepository;

    public Nota save(Nota nota) {

        this.anoRepository.save(nota.getInfo().getAno());

        this.cursoRepository.save(nota.getInfo().getCurso());

        this.universidadeRepository.save(nota.getInfo().getUniversidade());

        return this.notaRepository.save(nota);
    }

    public Optional<Nota> getNotaById(NotaId id) {
        return this.notaRepository.findById(id);
    }

    public List<Nota> getAll() {
        return this.notaRepository.findAll();
    }

    public Optional<Nota> getNota(NotaIdInterface id) {
        Optional<Ano> optAno = this.anoRepository.findById(id.getAno());

        CursoId cursoId = new CursoId(id.getCodigoCurso(), id.getModalidade());
        Optional<Curso> optCurso = this.cursoRepository.findById(cursoId);

        UniversidadeId universidadeId =
                new UniversidadeId(id.getCodigoIES(), id.getCodigoMunicipio());
        Optional<Universidade> optUniversidade =
                this.universidadeRepository.findById(universidadeId);

        NotaId notaId = new NotaId(optAno.get(), optCurso.get(), optUniversidade.get());

        return this.notaRepository.findById(notaId);
    }

    public void deleteNotaById(NotaId id) {
        this.notaRepository.deleteById(id);
    }

    public void deleteNota(NotaIdInterface id) {
        Optional<Ano> optAno = this.anoRepository.findById(id.getAno());

        CursoId cursoId = new CursoId(id.getCodigoCurso(), id.getModalidade());
        Optional<Curso> optCurso = this.cursoRepository.findById(cursoId);

        UniversidadeId universidadeId =
                new UniversidadeId(id.getCodigoIES(), id.getCodigoMunicipio());
        Optional<Universidade> optUniversidade =
                this.universidadeRepository.findById(universidadeId);

        NotaId notaId = new NotaId(optAno.get(), optCurso.get(), optUniversidade.get());

        this.notaRepository.deleteById(notaId);
    }

}
