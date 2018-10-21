package br.com.openenade.api.regiao;

import static org.junit.Assert.assertEquals;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegiaoControllerTests {
    
    @Autowired
    private RegiaoController controller;
    
    @Autowired
    private RegiaoService service;
    
    @Test
    public void testController() {
        Regiao regiao = new Regiao("NE");
        
        controller.postRegiao(regiao);
        
        this.service.save(regiao);
        
        assertEquals(new ResponseEntity<>(this.service.getAll(), HttpStatus.OK), controller.getAll());
        
        Optional<Regiao> optRegiao = this.service.getBySigla(regiao.getSigla());
        
        assertEquals(new ResponseEntity<>(optRegiao.get(), HttpStatus.OK),
                controller.getRegiaoBySigla("NE"));
        
        assertEquals(new ResponseEntity<>(HttpStatus.OK),
                controller.deleteRegiaoBySigla("NE"));
        
        assertEquals(new ResponseEntity<>(HttpStatus.NOT_FOUND),
                controller.getRegiaoBySigla("NE"));
    }

}
