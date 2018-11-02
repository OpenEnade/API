package br.com.openenade.api.universidade;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class UniversidadeControllerTests {

    @Autowired
    private UniversidadeService service;
    
    @Autowired
    private UniversidadeRepository repository;
    
    @Autowired
    private UniversidadeController controller;
    
    @Before
    @After
    public void cleanUp() {
        this.repository.deleteAll();
    }
    
    @Test
    public void controllerTest() {

    Regiao regiao = new Regiao("AA");
    Estado estado = new Estado("PK", regiao);
    Municipio campus = new Municipio((long) 10, estado, "zimbabue");
    
    List<Curso> cursos = new ArrayList<Curso>();
    cursos.add(new Curso("CC", 13, 10, Modalidade.EDUCACAO_PRESENCIAL));
    cursos.add(new Curso("EE", 13, 11, Modalidade.EDUCACAO_PRESENCIAL));
    
    Universidade univ = new Universidade((long) 10, "UFCG" , campus, CategoriaAdmin.PUBLICO , cursos);
    
    controller.postUniversidade(univ);
    
    this.service.save(univ);
    
    assertEquals(new ResponseEntity<>(this.service.getAll(), HttpStatus.OK), controller.getAll());
    
    Optional<Universidade> optUniversidade = this.service.getOptionalUniversidadeByCodigoIES(univ.getCodigoIES());
    
    assertEquals(optUniversidade.get(),
            controller.getUniversidadeByCodigoIES((long) 10));
    
    assertEquals(new ResponseEntity<>(HttpStatus.OK),
            controller.deleteUniversidade((long) 10));
    
    }
    
    @Test(expected = ResourceNotFound.class)
    public void deleteTests() {
        controller.getUniversidadeByCodigoIES((long) 10);
    }


    
}
