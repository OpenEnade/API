package br.com.openenade.api.curso;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.modalidade.Modalidade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CursoTests {

    @Test
    public void testCurso() {
        Curso curso = new Curso();

        assertNull(curso.getNome());
        assertNull(curso.getModalidade());
        assertNull(curso.getCodigoArea());
    }

    @Test
    public void testCursoStringLongLongModalidade() {
        Curso curso = new Curso("Ciência da Computação", 5, Modalidade.EDUCACAO_A_DISTANCIA);

        assertEquals("Ciência da Computação", curso.getNome());
        assertEquals(new Long(5), curso.getCodigoArea());
        assertEquals(Modalidade.EDUCACAO_A_DISTANCIA, curso.getModalidade());
    }

    @Test
    public void testEqualsObject() {
        Curso curso = new Curso("Ciência da Computação", 5, Modalidade.EDUCACAO_A_DISTANCIA);

        assertEquals(curso, curso);
        assertNotEquals(curso, null);

        Curso cursoA = new Curso("Ciência da Computação", 5, Modalidade.EDUCACAO_A_DISTANCIA);
        Curso cursoB = new Curso("Ciência da Computação", 5, Modalidade.EDUCACAO_A_DISTANCIA);

        assertEquals(cursoA, cursoB);

        cursoA.setNome("CC");
        assertEquals(cursoA, cursoB);

        cursoA.setCodigoArea(5L);
        assertEquals(cursoA, cursoB);

        cursoA.setModalidade(Modalidade.EDUCACAO_PRESENCIAL);
        assertNotEquals(cursoA, cursoB);
    }

    @Test
    public void testToString() {
        Curso curso = new Curso("Ciência da Computação", 5, Modalidade.EDUCACAO_PRESENCIAL);

        assertEquals(
                "Curso [nome=Ciência da Computação, codigoArea=5, modalidade=EDUCACAO_PRESENCIAL]",
                curso.toString());
    }

}
