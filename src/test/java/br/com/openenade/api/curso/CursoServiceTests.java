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
    private CursoService service;

    @Test
    public void testSaveBasic() {
        this.service.save(new Curso("CC", 13, 10, Modalidade.EDUCACAO_PRESENCIAL));
    }

    @Test
    public void testGetAllBasic() {
        Curso a = new Curso("Ciência da Computação", 3, 2, Modalidade.EDUCACAO_PRESENCIAL);
        Curso b = new Curso("Engenharia Mecânica", 5, 1, Modalidade.EDUCACAO_PRESENCIAL);
        Curso c = new Curso("Engenharia Elétrica", 7, 3, Modalidade.EDUCACAO_PRESENCIAL);
        Curso d = new Curso("História", 11, 4, Modalidade.EDUCACAO_A_DISTANCIA);

        Curso e = new Curso("História", 13, 5, Modalidade.EDUCACAO_A_DISTANCIA);

        this.service.save(a);
        this.service.save(b);
        this.service.save(c);
        this.service.save(d);

        List<Curso> cursos = this.service.getAll();

        assertEquals(4, cursos.size());

        assertTrue(cursos.contains(a));
        assertTrue(cursos.contains(b));
        assertTrue(cursos.contains(c));
        assertTrue(cursos.contains(d));

        assertFalse(cursos.contains(e));
    }

    @Test
    public void testGetByCodigo() {
        Curso a = new Curso("Ciência da Computação", 3, 2, Modalidade.EDUCACAO_PRESENCIAL);
        Curso b = new Curso("Engenharia Mecânica", 5, 1, Modalidade.EDUCACAO_PRESENCIAL);
        Curso c = new Curso("Engenharia Elétrica", 7, 3, Modalidade.EDUCACAO_PRESENCIAL);

        this.service.save(a);
        this.service.save(b);
        this.service.save(c);

        Curso aa = this.service.getByCodigo(2L).get();
        Curso cc = this.service.getByCodigo(3L).get();
        Curso bb = this.service.getByCodigo(1L).get();

        assertEquals(a, aa);
        assertEquals(b, bb);
        assertEquals(c, cc);
    }

}
