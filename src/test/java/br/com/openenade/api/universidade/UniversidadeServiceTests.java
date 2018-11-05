package br.com.openenade.api.universidade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.modalidade.Modalidade;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.regiao.Regiao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UniversidadeServiceTests {
    
    @Autowired
    private UniversidadeService service;
    
    @Autowired
    private UniversidadeRepository repository;
    
    private Regiao nordeste = new Regiao("NE");
    
    private Estado pb = new Estado("pb", nordeste);
    
    private Municipio campus = new Municipio((long) 10, pb, "cg");
    

    @Before
    @After
    public void cleanUp() {
        this.repository.deleteAll();
    }
    
    
    @Test
    public void saveTest() {
        
        List<Curso> cursos = new ArrayList<Curso>();
        cursos.add(new Curso("CC", 13, 10, Modalidade.EDUCACAO_PRESENCIAL));
        cursos.add(new Curso("EE", 13, 11, Modalidade.EDUCACAO_PRESENCIAL));
        
        Universidade ufcg = new Universidade((long) 10, "UFCG" , campus, CategoriaAdmin.PUBLICO , cursos);
                
        this.service.save(ufcg);
        Universidade aux = this.service.getUniversidadeByCodigoIES(ufcg.getCodigoIES());
        assertEquals("UFCG", aux.getNome());
        
    }
    
    @Test
    public void getAllTest() {

        List<Curso> cursos = new ArrayList<Curso>();
        cursos.add(new Curso("CC", 13, 10, Modalidade.EDUCACAO_PRESENCIAL));
        cursos.add(new Curso("EE", 13, 11, Modalidade.EDUCACAO_PRESENCIAL));
        
        Municipio campusJP = new Municipio((long) 10, pb, "jp");
        
        Universidade UFCG = new Universidade((long) 10, "UFCG" , campus, CategoriaAdmin.PUBLICO , cursos);
        Universidade UFPB = new Universidade((long) 11, "UFPB" , campusJP, CategoriaAdmin.PUBLICO , cursos);
        Universidade Nassau = new Universidade((long) 12, "UEPB" , campus, CategoriaAdmin.PRIVADO , cursos);
        
        
        this.service.save(UFCG);
        this.service.save(UFPB);
        
        List<Universidade> universidades = this.service.getAll();
        
        assertEquals(2, universidades.size());
        
        assertTrue(universidades.contains(UFCG));
        assertTrue(universidades.contains(UFPB));
        assertFalse(universidades.contains(Nassau));
        
        
    }
    
    @Test
    public void getByCodigoIESTest() {
        
        List<Curso> cursos = new ArrayList<Curso>();
        cursos.add(new Curso("CC", 13, 10, Modalidade.EDUCACAO_PRESENCIAL));
        cursos.add(new Curso("EE", 13, 11, Modalidade.EDUCACAO_PRESENCIAL));
        
        Universidade UFPB = new Universidade((long) 10, "UFPB" , campus, CategoriaAdmin.PUBLICO , cursos);
        Universidade Nassau = new Universidade((long) 12, "UEPB" , campus, CategoriaAdmin.PRIVADO , cursos);
        
        
        this.service.save(UFPB);
        this.service.save(Nassau);
        
        
        Universidade aux = this.service.getUniversidadeByCodigoIES((long) 10);
        
        assertEquals(aux, UFPB);
        
       
    }
    
    @Test (expected = ResourceNotFound.class)
    public void deleteUniversidadeByCodigoIESTest() {
        
        List<Curso> cursos = new ArrayList<Curso>();
        cursos.add(new Curso("CC", 13, 10, Modalidade.EDUCACAO_PRESENCIAL));
        cursos.add(new Curso("EE", 13, 11, Modalidade.EDUCACAO_PRESENCIAL));
        
        Universidade UFCG = new Universidade((long) 10, "UFCG" , campus, CategoriaAdmin.PUBLICO , cursos);
        
        this.service.save(UFCG);
        
        this.service.deleteUniversidadeByCodigoIES((long) 10);
        
        this.service.getUniversidadeByCodigoIES((long) 10);
    }

}