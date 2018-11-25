package br.com.openenade.api.universidade;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.BaseUnitTest;
import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.curso.CursoService;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.modalidade.Modalidade;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.regiao.Regiao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniversidadeControllerTests extends BaseUnitTest {

    @Autowired
    private UniversidadeService service;

    @Autowired
    private UniversidadeController controller;

    @Autowired
    private CursoService cursoService;
    
    @Test
    public void controllerTest() {
        Regiao regiao = new Regiao("AA");
        Estado estado = new Estado("PK", regiao);
        Municipio campus = new Municipio((long) 10, estado, "zimbabue");

        Set<Curso> cursos = createCursos();

        Universidade univ =
                new Universidade((long) 10, "UFCG", campus, CategoriaAdmin.PUBLICO, cursos);

        this.controller.addUniversidade(univ);

        Assert.assertEquals(new ResponseEntity<>(this.service.getAll(), HttpStatus.OK),
                controller.getAll());

        Collection<Universidade> universidades =
                this.service.getAllByCodigoIES(univ.getCodigoIES());

        Assert.assertEquals(universidades.size(), 1);
        Assert.assertEquals(universidades,
                controller.getUniversidadesByCodigoIES((long) 10).getBody());

        // Issue57
        Collection<Universidade> matchedUniversidades =
                this.service.getAllUniversidadesByCursoNome("CC");

        Assert.assertEquals(matchedUniversidades.size(), 1);
        Assert.assertTrue(matchedUniversidades.contains(univ));

    }

    @Test
    public void deleteTests() {
        Regiao regiao = new Regiao("AA");
        Estado estado = new Estado("PK", regiao);
        Municipio campus = new Municipio((long) 10, estado, "zimbabue");

        Set<Curso> cursos = createCursos();

        Universidade univ =
                new Universidade((long) 10, "UFCG", campus, CategoriaAdmin.PUBLICO, cursos);

        this.controller.addUniversidade(univ);

        Assert.assertEquals(new ResponseEntity<>(HttpStatus.OK),
                this.controller.deleteUniversidadesByCodigoCurso((long) 10));

        Assert.assertTrue(
                this.controller.getUniversidadesByCodigoIES(new Long(10)).getBody().isEmpty());
    }

    private Set<Curso> createCursos() {
        Set<Curso> cursos = new HashSet<Curso>();
        Curso cursoCC =
                this.cursoService.save(new Curso("CC", 13, 10, Modalidade.EDUCACAO_PRESENCIAL));
        Curso cursoEE =
                this.cursoService.save(new Curso("EE", 13, 11, Modalidade.EDUCACAO_PRESENCIAL));
        cursos.add(cursoCC);
        cursos.add(cursoEE);
        return cursos;
    }

}
