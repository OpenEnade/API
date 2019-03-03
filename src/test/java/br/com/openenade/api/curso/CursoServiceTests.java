package br.com.openenade.api.curso;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.BaseUnitTest;
import br.com.openenade.api.modalidade.Modalidade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CursoServiceTests extends BaseUnitTest {

    @Autowired
    private CursoService cursoService;


    @Autowired
    private CursoRepository cursoRepository;

    @Test
    public void testSaveBasic() {
        this.cursoService.addCurso(new Curso("CC", 13, Modalidade.EDUCACAO_PRESENCIAL));
    }

    @Test
    public void testGetAllBasic() {
        Curso a = new Curso("Ciência da Computação", 3, Modalidade.EDUCACAO_PRESENCIAL);
        Curso b = new Curso("Engenharia Mecânica", 5, Modalidade.EDUCACAO_PRESENCIAL);
        Curso c = new Curso("Engenharia Elétrica", 7, Modalidade.EDUCACAO_PRESENCIAL);
        Curso d = new Curso("História", 11, Modalidade.EDUCACAO_A_DISTANCIA);

        Curso e = new Curso("História", 13, Modalidade.EDUCACAO_A_DISTANCIA);

        this.cursoService.addCurso(a);
        this.cursoService.addCurso(b);
        this.cursoService.addCurso(c);
        this.cursoService.addCurso(d);

        List<Curso> cursos = this.cursoService.getAll();

        assertEquals(4, cursos.size());

        assertTrue(cursos.contains(a));
        assertTrue(cursos.contains(b));
        assertTrue(cursos.contains(c));
        assertTrue(cursos.contains(d));

        assertFalse(cursos.contains(e));
    }

    @Test
    public void testGetByCodigo() {

        String[] cursos = {"Ciência da Computação", "Engenharia Mecânica", "Engenharia Elétrica"};

        Modalidade modalidade = Modalidade.EDUCACAO_PRESENCIAL;

        Curso a = new Curso(cursos[0], 3, modalidade);
        Curso b = new Curso(cursos[1], 5, modalidade);
        Curso c = new Curso(cursos[2], 7, modalidade);

        this.cursoService.addCurso(a);
        this.cursoService.addCurso(b);
        this.cursoService.addCurso(c);

        Curso aa = this.cursoService.getCursoByCodigo(3L, modalidade);
        Curso bb = this.cursoService.getCursoByCodigo(5L, modalidade);
        Curso cc = this.cursoService.getCursoByCodigo(7L, modalidade);

        assertEquals(a, aa);
        assertEquals(b, bb);
        assertEquals(c, cc);
    }

    @Test
    public void testCursoIdClass() {
        String[] cursos = {"Ciência da Computação", "Engenharia Mecânica", "Engenharia Elétrica"};

        Curso curso = new Curso(cursos[0], 3L, Modalidade.EDUCACAO_A_DISTANCIA);
        this.cursoRepository.save(curso);

        CursoId id = new CursoId(3L, Modalidade.EDUCACAO_A_DISTANCIA);

        assertEquals(curso, this.cursoRepository.findById(id).get());

    }
}
