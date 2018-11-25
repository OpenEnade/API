package br.com.openenade.api.universidade;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.curso.CursoService;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.municipio.MunicipioService;

@Service
@Transactional
public class UniversidadeService {

    @Autowired
    private UniversidadeRepository repository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private MunicipioService municipioService;

    public Universidade save(Universidade universidade) {
        Optional<Municipio> optMunicipio =
                this.municipioService.getOptionalByCodigo(universidade.getCampus().getCodigo());
        Municipio newCampus;
        if (optMunicipio.isPresent()) {
            newCampus = optMunicipio.get();
        } else {
            newCampus = this.municipioService.save(universidade.getCampus());
        }
        universidade.setCampus(newCampus);
        return this.repository.save(universidade);
    }

    public Universidade addCurso2Universidade(Universidade universidade, Curso curso) {
        Optional<Curso> optCurso =
                this.cursoService.getByCodigo(curso.getCodigoCurso(), curso.getModalidade());
        if (optCurso.isPresent()) {
            universidade.addCurso(optCurso.get());
            return this.repository.saveAndFlush(universidade);
        } else {
            throw new ResourceNotFound(curso.toString());
        }
    }

    public Collection<Universidade> getAll() {
        return this.repository.findAll();
    }

    // Issue #38
    public Collection<Universidade> getAllByCodigoIES(Long codigoIES) {
        return this.repository.findAllByCodigoIES(codigoIES);
    }

    public Universidade getUniversidadeById(Long codigoIES, Long codigoMunicipio) {
        Optional<Universidade> optUnivesidade =
                this.repository.findById(new UniversidadeId(codigoIES, codigoMunicipio));
        if (optUnivesidade.isPresent()) {
            return optUnivesidade.get();
        } else {
            throw new ResourceNotFound("" + codigoIES);
        }
    }

    public Optional<Universidade> getOptUniversidadeById(Long codigoIES, Long codigoMunicipio) {
        return this.repository.findById(new UniversidadeId(codigoIES, codigoMunicipio));
    }

    public void deleteUniversidadesByCodigoIES(Long codigoIES) {
        this.repository.deleteAllByCodigoIES(codigoIES);
    }

    public void deleteUniversidadesByMunicipioCodigo(Municipio campus) {
        this.repository.deleteAllByCampus(campus);
    }

    public void deleteUniversidadeById(Long codigoIES, Municipio campus) {
        UniversidadeId id = new UniversidadeId(codigoIES, campus.getCodigo());
        this.repository.deleteById(id);
    }

    public Collection<Universidade> getAllUniversidadesByCursoNome(String nomeCurso) {
        Collection<Universidade> universidades = this.repository.findAll();
        return universidades.stream()
                .filter(universidade -> universidade.getCursos().stream()
                        .anyMatch(curso -> curso.getNome().equals(nomeCurso)))
                .collect(Collectors.toList());
    }
}
