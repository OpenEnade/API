package br.com.openenade.api.curso;

import static org.junit.Assert.*;
import org.junit.Test;
import br.com.openenade.api.modalidade.Modalidade;

public class CursoTests {

    @Test
    public void testCurso() {
        Curso curso = new Curso();

        assertNull(curso.getNome());
        assertNull(curso.getModalidade());
        assertNull(curso.getCodigoArea());
        assertNull(curso.getCodigoCurso());
    }

    @Test
    public void testCursoStringLongLongModalidade() {
        Curso curso = new Curso("Ciência da Computação", 5, 7, Modalidade.EDUCACAO_A_DISTANCIA);

        assertEquals("Ciência da Computação", curso.getNome());
        assertEquals(new Long(5), curso.getCodigoArea());
        assertEquals(new Long(7), curso.getCodigoCurso());
        assertEquals(Modalidade.EDUCACAO_A_DISTANCIA, curso.getModalidade());
    }

    @Test
    public void testEqualsObject() {
        Curso curso = new Curso("Ciência da Computação", 5, 7, Modalidade.EDUCACAO_A_DISTANCIA);

        assertEquals(curso, curso);
        assertNotEquals(curso, null);

        Curso cursoA = new Curso("Ciência da Computação", 5, 7, Modalidade.EDUCACAO_A_DISTANCIA);
        Curso cursoB = new Curso("Ciência da Computação", 5, 7, Modalidade.EDUCACAO_A_DISTANCIA);

        assertEquals(cursoA, cursoB);
        assertEquals(cursoA.hashCode(), cursoB.hashCode());

        cursoA.setNome("CC");

        assertEquals(cursoA, cursoB);
        assertEquals(cursoA.hashCode(), cursoB.hashCode());

        cursoA.setCodigoArea(5L);

        assertEquals(cursoA, cursoB);
        assertEquals(cursoA.hashCode(), cursoB.hashCode());

        cursoA.setModalidade(Modalidade.EDUCACAO_PRESENCIAL);

        assertEquals(cursoA, cursoB);
        assertEquals(cursoA.hashCode(), cursoB.hashCode());

        cursoA.setCodigoCurso(99L);

        assertNotEquals(cursoA, cursoB);

        cursoA.setCodigoCurso(null);

        assertNotEquals(cursoA, cursoB);
        assertNotEquals(cursoA.hashCode(), cursoB.hashCode());

        cursoB.setCodigoCurso(null);

        assertEquals(cursoA, cursoB);
        assertEquals(cursoA.hashCode(), cursoB.hashCode());
    }

    @Test
    public void testToString() {
        Curso curso = new Curso("Ciência da Computação", 5, 7, Modalidade.EDUCACAO_PRESENCIAL);

        assertEquals(
                "Curso [codigoCurso=7, nome=Ciência da Computação, codigoArea=5, modalidade=EDUCACAO_PRESENCIAL]",
                curso.toString());
    }

}
