package br.com.openenade.api.nota;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.ano.AnoRepository;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.curso.CursoRepository;
import br.com.openenade.api.universidade.Universidade;
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
        Optional<Ano> optAno = this.anoRepository.findById(nota.getAno().getAno());

        Curso curso = nota.getCurso();
        Optional<Curso> optCurso = this.cursoRepository.findOne(Example.of(curso));
        Optional<Universidade> optUniversidade =
                this.universidadeRepository.findById(nota.getUniversidade().getCodigoIES());

        if (!optAno.isPresent()) {
            this.anoRepository.save(nota.getAno());
        }

        if (!optCurso.isPresent()) {
            this.cursoRepository.save(nota.getCurso());
        }

        if (!optUniversidade.isPresent()) {
            this.universidadeRepository.save(nota.getUniversidade());
        }

        return this.notaRepository.save(nota);
    }
    
    public Optional<Nota> getNotaByIndex(Integer index) {
        return this.notaRepository.findById(index);
    }

    public List<Nota> getAll() {
        return this.notaRepository.findAll();
    }

}
