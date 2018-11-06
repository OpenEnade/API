package br.com.openenade.api.estado;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.regiao.Regiao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstadoControllerTests {

    @Autowired
    private EstadoController controller;

    @Autowired
    private EstadoService service;

    @Autowired
    private EstadoRepository repository;

    @Before
    @After
    public void cleanUp() {
        for (Estado estado : this.repository.findAll()) {
            this.service.deleteEstadoById(estado.getSigla());
        }
    }

    @Test(expected = ResourceNotFound.class)
    public void estadoControllerTests() {
        Regiao regiao = new Regiao("AA");
        Estado estado = new Estado("PK", regiao);

        controller.postEstado(estado);

        assertEquals(new ResponseEntity<>(this.service.getAll(), HttpStatus.OK),
                controller.getAll());

        Estado optEstado = this.service.getBySiglaEstado(estado.getSigla());

        assertEquals(optEstado, controller.getEstadoBySigla("PK"));

        assertEquals(new ResponseEntity<>(HttpStatus.OK), controller.deleteEstadoBySigla("PK"));

        controller.getEstadoBySigla("PK");
    }
}
