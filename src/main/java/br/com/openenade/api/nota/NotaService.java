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
import br.com.openenade.api.modalidade.Modalidade;
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

	public Optional<Nota> getNota(Integer ano, Long codigoCurso, Modalidade modalidade, Long codigoIES,
			Long codigoMunicipio) {

		Optional<Ano> optAno = this.anoRepository.findById(ano);

		CursoId cursoId = new CursoId(codigoCurso, modalidade);
		Optional<Curso> optCurso = this.cursoRepository.findById(cursoId);

		UniversidadeId universidadeId = new UniversidadeId(codigoIES, codigoMunicipio);
		Optional<Universidade> optUniversidade = this.universidadeRepository.findById(universidadeId);

		NotaId notaId = new NotaId(optAno.get(), optCurso.get(), optUniversidade.get());

		return this.notaRepository.findById(notaId);
	}

	public List<Nota> getAll() {
		return this.notaRepository.findAll();
	}

	public boolean deleteNotaById(NotaId id) {
		if (this.notaRepository.existsById(id)) {
			this.notaRepository.deleteById(id);
		} else {
			return false;
		}

		return true;
	}

	public boolean deleteNota(Integer ano, Long codigoCurso, Modalidade modalidade, Long codigoIES,
			Long codigoMunicipio) {

		Optional<Ano> optAno = this.anoRepository.findById(ano);

		CursoId cursoId = new CursoId(codigoCurso, modalidade);
		Optional<Curso> optCurso = this.cursoRepository.findById(cursoId);

		UniversidadeId universidadeId = new UniversidadeId(codigoIES, codigoMunicipio);
		Optional<Universidade> optUniversidade = this.universidadeRepository.findById(universidadeId);

		NotaId notaId = new NotaId(optAno.get(), optCurso.get(), optUniversidade.get());

		this.notaRepository.deleteById(notaId);

		return !this.notaRepository.findById(notaId).isPresent();
	}

}
