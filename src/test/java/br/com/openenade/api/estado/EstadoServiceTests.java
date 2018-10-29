package br.com.openenade.api.estado;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.regiao.Regiao;
import br.com.openenade.api.regiao.RegiaoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstadoServiceTests {

    @Autowired
    private EstadoService service;

    @Autowired
    private RegiaoService regiaoService;

    @After
    @Before
    public void cleanRepository() {
        for (Regiao regiao : this.regiaoService.getAll()) {
            this.regiaoService.deleteRegiaoBySigla(regiao.getSigla());
        }
    }

    @Test
    public void saveTest() {
        Regiao regiao = new Regiao("DF");
        Estado estado = new Estado("AS", regiao);
        this.service.save(estado);
        Estado aux = this.service.getBySiglaEstado("AS");
        assertEquals("AS", aux.getSigla());
    }

    @Test
    public void getAllTest() {
 
        Regiao df = new Regiao("DF");
        Regiao sp = new Regiao("SP");

        Estado as = new Estado("AS", df);
        Estado es = new Estado("ES", sp);
        Estado te = new Estado("TE", sp);


        this.service.save(as);
        this.service.save(es);

        List<Estado> estados = this.service.getAll();

        assertEquals(2, estados.size());

        assertTrue(estados.contains(as));
        assertTrue(estados.contains(es));
        assertFalse(estados.contains(te));
    }

    @Test
    public void getBySiglaEstadoTest() {

        Regiao df = new Regiao("DF");

        Estado as = new Estado("AS", df);
        Estado es = new Estado("ES", df);

        this.service.save(as);
        this.service.save(es);

        Estado estado = this.service.getBySiglaEstado("ES");

        assertEquals(estado, es);
    }

    @Test(expected = ResourceNotFound.class)
    public void deleteEstadoBySiglaTest() {

        Regiao df = new Regiao("DF");

        Estado as = new Estado("AS", df);

        this.service.save(as);

        this.service.deleteEstadoBySiglaEstado("AS");

        this.service.getBySiglaEstado(as.getSigla());
    }


}
