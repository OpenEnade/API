package br.com.openenade.api.universidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.BaseUnitTest;
import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.curso.CursoService;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.modalidade.Modalidade;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.regiao.Regiao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniversidadeServiceTests extends BaseUnitTest {

	@Autowired
	private UniversidadeService service;

	@Autowired
	private CursoService cursoService;

	private Regiao nordeste = new Regiao("NE");

	private Estado pb = new Estado("pb", nordeste);

	private Municipio campus = new Municipio((long) 10, pb, "cg");

	@Test
	public void saveTest() {
		Set<Curso> cursos = createCursos();

		Universidade ufcg = new Universidade((long) 10, "UFCG", campus, CategoriaAdmin.PUBLICO, cursos);

		this.service.save(ufcg);
		Universidade aux = this.service.getUniversidadeByCodigoIES(ufcg.getCodigoIES());
		assertEquals("UFCG", aux.getNome());
	}

	@Test
	public void getAllTest() {
		Set<Curso> cursos = createCursos();

		Municipio campusJP = new Municipio((long) 10, pb, "jp");

		Universidade UFCG = new Universidade((long) 10, "UFCG", campus, CategoriaAdmin.PUBLICO, cursos);
		Universidade UFPB = new Universidade((long) 11, "UFPB", campusJP, CategoriaAdmin.PUBLICO, cursos);
		Universidade Nassau = new Universidade((long) 12, "UEPB", campus, CategoriaAdmin.PRIVADO, cursos);

		this.service.save(UFCG);
		this.service.save(UFPB);

		Collection<Universidade> universidades = this.service.getAll();

		assertEquals(2, universidades.size());

		assertTrue(universidades.contains(UFCG));
		assertTrue(universidades.contains(UFPB));
		assertFalse(universidades.contains(Nassau));

	}

	@Test
	public void getByCodigoIESTest() {
		Set<Curso> cursos = createCursos();

		Universidade UFPB = new Universidade((long) 10, "UFPB", campus, CategoriaAdmin.PUBLICO, cursos);
		Universidade Nassau = new Universidade((long) 12, "UEPB", campus, CategoriaAdmin.PRIVADO, cursos);

		this.service.save(UFPB);
		this.service.save(Nassau);

		Universidade aux = this.service.getUniversidadeByCodigoIES((long) 10);
		assertEquals(aux, UFPB);
	}

	@Test(expected = ResourceNotFound.class)
	public void deleteUniversidadeByCodigoIESTest() {
		Set<Curso> cursos = createCursos();

		Universidade UFCG = new Universidade((long) 10, "UFCG", campus, CategoriaAdmin.PUBLICO, cursos);

		this.service.save(UFCG);

		this.service.deleteUniversidadeByCodigoIES((long) 10);

		this.service.getUniversidadeByCodigoIES((long) 10);
	}

	private Set<Curso> createCursos() {
		Set<Curso> cursos = new HashSet<Curso>();
		Curso cursoCC = this.cursoService.save(new Curso("CC", 13, 10, Modalidade.EDUCACAO_PRESENCIAL));
		Curso cursoEE = this.cursoService.save(new Curso("EE", 13, 11, Modalidade.EDUCACAO_PRESENCIAL));
		cursos.add(cursoCC);
		cursos.add(cursoEE);
		return cursos;
	}

	@Test
	public void getUniversidadeById() {

		Set<Curso> cursos = createCursos();
		Universidade ufcg = new Universidade((long) 10, "UFCG", campus, CategoriaAdmin.PUBLICO, cursos);

		this.service.save(ufcg);
		assertEquals(ufcg, this.service.getUniversidadeById(ufcg.getCodigoIES(), ufcg.getCampus().getCodigo()).get());

	}
	
	@Test
	public void getUniversidadeByCurso() {
		Set<Curso> cursosEmpty = new HashSet<Curso>();
		Set<Curso> cursos = createCursos();
		Universidade ufcg = new Universidade((long) 10, "UFCG", campus, CategoriaAdmin.PUBLICO, cursos);
		Universidade uepb = new Universidade((long) 11, "UEPB", campus, CategoriaAdmin.PUBLICO, cursos);
		Universidade ufpb = new Universidade((long) 12, "UFPB", campus, CategoriaAdmin.PUBLICO, cursosEmpty);

		this.service.save(ufcg);
		this.service.save(uepb);
		this.service.save(ufpb);
		
		Collection<Universidade> expect = new HashSet<Universidade>();
		expect.add(ufcg);
		expect.add(uepb);
		
		assertEquals( expect , this.service.getAllByCurso((long) 10, Modalidade.EDUCACAO_PRESENCIAL));

	}

	@Test
	public void deleteUniversidadeById() {

		Set<Curso> cursos = createCursos();
		Universidade ufcg = new Universidade((long) 10, "UFCG", campus, CategoriaAdmin.PUBLICO, cursos);

		this.service.save(ufcg);

		assertEquals(ufcg, this.service.getUniversidadeById(ufcg.getCodigoIES(), ufcg.getCampus().getCodigo()).get());

		this.service.deleteUniversidadeById(ufcg.getCodigoIES(), ufcg.getCampus());

		assertFalse(this.service.getUniversidadeById(ufcg.getCodigoIES(), ufcg.getCampus().getCodigo()).isPresent());

	}

}
