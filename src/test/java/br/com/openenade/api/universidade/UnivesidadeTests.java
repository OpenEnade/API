package br.com.openenade.api.universidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.modalidade.Modalidade;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.regiao.Regiao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnivesidadeTests {


    
    @Test
    public void testUniversidade() {
        Regiao regiao = new Regiao("OO");
        Estado estado = new Estado("EE", regiao);
        Municipio campus = new Municipio((long) 10, estado, null);
        
        List<Curso> cursos = new ArrayList<Curso>();
        cursos.add(new Curso("CC", 13, 10, Modalidade.EDUCACAO_PRESENCIAL));
        cursos.add(new Curso("EE", 13, 11, Modalidade.EDUCACAO_PRESENCIAL));
        
        Universidade univ = new Universidade((long) 10, null , campus, CategoriaAdmin.PUBLICO , cursos);
        
        Universidade univ2 = new Universidade((long) 10, "" , campus, CategoriaAdmin.PUBLICO , cursos);
        
        assertNull(univ.getNome());
        assertEquals("", univ2.getNome());
    }

    @Test
    public void testEqualsUniversidade() {
        Regiao nordeste = new Regiao("ND");
        
        Estado pe = new Estado("PE", nordeste);
        Estado pb = new Estado("PB", nordeste);
        
        Municipio campus1 = new Municipio((long) 10, pe, "Coxixola");
        Municipio campus2 = new Municipio((long) 9, pb, "Souza");
        
        List<Curso> cursos = new ArrayList<Curso>();
        cursos.add(new Curso("CC", 13, 10, Modalidade.EDUCACAO_PRESENCIAL));
        cursos.add(new Curso("EE", 13, 11, Modalidade.EDUCACAO_PRESENCIAL));
        
        Universidade ufcg = new Universidade((long) 10, "UFCG" , campus1, CategoriaAdmin.PUBLICO , cursos);
        
        Universidade univ2 = new Universidade((long) 5, "UFCG" , campus2, CategoriaAdmin.PUBLICO , cursos);
        
        assertNotEquals(ufcg, null);
        assertNotEquals(ufcg, univ2);
        
        univ2.setCodigoIES((long) 10);
            
        assertEquals(ufcg, univ2);
       
    }
    
    @Test
    public void testToStringUniversidade() {
        Regiao regiao = new Regiao("EUA");
        Estado estado = new Estado("CA", regiao);
        Municipio campus = new Municipio((long) 10, estado, "Coxixola");

        List<Curso> cursos = new ArrayList<Curso>();
        cursos.add(new Curso("CC", 13, 10, Modalidade.EDUCACAO_PRESENCIAL));
        cursos.add(new Curso("EE", 13, 11, Modalidade.EDUCACAO_PRESENCIAL));

        Universidade ufcg = new Universidade((long) 10, "UFCG" , campus, CategoriaAdmin.PUBLICO , cursos);
        
        assertEquals("Universidade [codigoIES=10, nome=UFCG, campus=Municipio "
                + "[codigo=10, estado=Estado "
                + "[siglaEstado=CA, regiaoEstado=Regiao [sigla=EUA]], nome=Coxixola],"
                + " categoriaAdmin=PUBLICO,"
                + " curso=[Curso [codigoCurso=10, nome=CC, codigoArea=13, modalidade=EDUCACAO_PRESENCIAL],"
                + " Curso [codigoCurso=11, nome=EE, codigoArea=13, modalidade=EDUCACAO_PRESENCIAL]]]", ufcg.toString());
    }
    
}
