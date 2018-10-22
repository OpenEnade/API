package br.com.openenade.api.estado;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.regiao.Regiao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstadoServiceTests {
    
    @Autowired
    private EstadoService service;
    
    @Autowired
    private EstadoRepository repository;
    
    
    @Test
    public void saveTest() {
        Regiao regiao = new Regiao("DF");
        Estado estado = new Estado("AS", regiao);
        this.service.save(estado);
        Estado aux = this.service.getBySiglaEstado("AS").get();
        assertEquals("AS", aux.getSiglaEstado());
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
        this.repository.deleteAll();
        
        Regiao df = new Regiao("DF");
        
        Estado as = new Estado("AS", df);
        Estado es = new Estado("ES", df);
        
        this.service.save(as);
        this.service.save(es);
        
        
        Estado estado = this.service.getBySiglaEstado("ES").get();
        
        assertEquals(estado, es);
        
       
    }
    
    @Test
    public void deleteEstadoBySiglaTest() {
        this.repository.deleteAll();
        
        Regiao df = new Regiao("DF");
        
        Estado as = new Estado("AS", df);
        
        this.service.save(as);
        
        this.service.deleteEstadoBySiglaEstado("AS");
        
        assertEquals(Optional.empty(), this.service.getBySiglaEstado(as.getSiglaEstado()));
    }
    
    
}
