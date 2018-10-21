package br.com.openenade.api.regiao;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegiaoServiceTests {

    @Autowired
    private RegiaoService service;
    
    @Autowired
    private RegiaoRepository repository;
    
    @Test
    public void saveTest() {
        Regiao regiao = new Regiao("DF");
        this.service.save(regiao);
        Regiao aux = this.service.getBySigla("DF").get();
        assertEquals("DF", aux.getSigla());
    }
    
    @Test
    public void getAllTest() {
        Regiao df = new Regiao("DF");
        Regiao sp = new Regiao("SP");
        Regiao pb = new Regiao("PB");
        Regiao ba = new Regiao("BA");
        
        this.service.save(df);
        this.service.save(sp);
        this.service.save(pb);
        
        List<Regiao> regioes = this.service.getAll();
        
        assertEquals(3, regioes.size());
        
        assertTrue(regioes.contains(df));
        assertTrue(regioes.contains(sp));
        assertFalse(regioes.contains(ba));
        
    }
    
    @Test
    public void getBySiglaTest() {
        this.repository.deleteAll();
        
        Regiao df = new Regiao("DF");
        Regiao sp = new Regiao("SP");
        Regiao pb = new Regiao("PB");
        
        this.service.save(df);
        this.service.save(sp);
        this.service.save(pb);
        
        
        Regiao regiao = this.service.getBySigla("SP").get();
        
        assertEquals(regiao, sp);
        
       
        
    }
    
    @Test
    public void deleteRegiaoBySiglaTest() {
        this.repository.deleteAll();
        
        Regiao df = new Regiao("DF");
        
        this.service.save(df);
        
        this.service.deleteRegiaoBySigla(df.getSigla());
        
        assertEquals(Optional.empty(), this.service.getBySigla(df.getSigla()));
    }
}
