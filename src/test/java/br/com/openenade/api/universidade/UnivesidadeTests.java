package br.com.openenade.api.universidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.HashSet;
import java.util.Set;
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
        
        Set<Curso> cursos = new HashSet<Curso>();
        cursos.add(new Curso("CC", 13, Modalidade.EDUCACAO_PRESENCIAL));
        cursos.add(new Curso("EE", 13, Modalidade.EDUCACAO_PRESENCIAL));
        
        Universidade univ = new Universidade((long) 10, null , campus, CategoriaAdmin.PUBLICO , cursos);
        
        Universidade univ2 = new Universidade((long) 10, "" , campus, CategoriaAdmin.PUBLICO , cursos);
        
        assertNull(univ.getNome());
        assertEquals("", univ2.getNome());
    }

}
