package br.com.openenade.api.curso;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.modalidade.Modalidade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CursoServiceTests {

    @Autowired
    private CursoService cursoService;

    @Autowired
    CursoRepository cursoRepository;

    @After
    @Before
    public void cleanRepository() {
        this.cursoRepository.deleteAll();
    }

    @Test
    public void testSaveBasic() {
        this.cursoService.save(new Curso("CC", 13, 10, Modalidade.EDUCACAO_PRESENCIAL));
    }

    @Test
    public void testGetAllBasic() {
        Curso a = new Curso("Ciência da Computação", 3, 2, Modalidade.EDUCACAO_PRESENCIAL);
        Curso b = new Curso("Engenharia Mecânica", 5, 1, Modalidade.EDUCACAO_PRESENCIAL);
        Curso c = new Curso("Engenharia Elétrica", 7, 3, Modalidade.EDUCACAO_PRESENCIAL);
        Curso d = new Curso("História", 11, 4, Modalidade.EDUCACAO_A_DISTANCIA);

        Curso e = new Curso("História", 13, 5, Modalidade.EDUCACAO_A_DISTANCIA);

        this.cursoService.save(a);
        this.cursoService.save(b);
        this.cursoService.save(c);
        this.cursoService.save(d);

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
        
        Long[] codigos = {(long) 111,(long) 123,(long) 312};
        
        Modalidade modalidade = Modalidade.EDUCACAO_PRESENCIAL;

        
        Curso a = new Curso(cursos[0], 3, codigos[0], modalidade);
        Curso b = new Curso(cursos[1], 5, codigos[1], modalidade);
        Curso c = new Curso(cursos[2], 7, codigos[2], modalidade);
        
        this.cursoService.save(a);
        this.cursoService.save(b);
        this.cursoService.save(c);

        Curso aa = this.cursoService.getByCodigo(codigos[0], modalidade).get();
        Curso bb = this.cursoService.getByCodigo(codigos[1], modalidade).get();
        Curso cc = this.cursoService.getByCodigo(codigos[2], modalidade).get();

        assertEquals(a, aa);
        assertEquals(b, bb);
        assertEquals(c, cc);
    }

}
