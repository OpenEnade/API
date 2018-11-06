package br.com.openenade.api.municipio;

import static org.junit.Assert.assertEquals;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.BaseUnitTest;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.regiao.Regiao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MunicipioControllerTests extends BaseUnitTest {

    @Autowired
    private MunicipioService service;
    
    @Autowired
    private MunicipioController controller;
    
    @Test
    public void controllerTest() {

    Regiao regiao = new Regiao("AA");
    Estado estado = new Estado("PK", regiao);
    Municipio municipio = new Municipio((long) 10, estado, "zimbabue");
    
    controller.postMunicipio(municipio);
    
    this.service.save(municipio);
    
    assertEquals(new ResponseEntity<>(this.service.getAll(), HttpStatus.OK), controller.getAll());
    
    Optional<Municipio> optMunicipio = this.service.getOptionalByCodigo(municipio.getCodigo());
    
    assertEquals(optMunicipio.get(),
            controller.getMunicipioByCodigo((long) 10));
    
    assertEquals(new ResponseEntity<>(HttpStatus.OK),
            controller.deleteMunicipio((long) 10));
    
    }
    
    @Test(expected = ResourceNotFound.class)
    public void deleteTests() {
        controller.getMunicipioByCodigo((long) 10);
    }
}



