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

}
