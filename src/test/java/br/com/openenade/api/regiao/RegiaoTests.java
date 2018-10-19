package br.com.openenade.api.regiao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegiaoTests {
    
    @Test
    public void testRegiao() {
        Regiao regiao = new Regiao(null);
        
        assertNull(regiao.getSigla());
    }

    @Test
    public void testEqualsRegiao() {
        Regiao regiaoPb = new Regiao("PB");
        Regiao regiaoDf = new Regiao("DF");
        Regiao regiaoParaiba = new Regiao("PB");
        Regiao regiao = new Regiao(null);
        
        assertNotEquals(regiao, null);
        assertNotEquals(regiaoPb, regiaoDf);
        
        assertEquals(regiaoPb.hashCode(), regiaoParaiba.hashCode());
        assertEquals(regiaoPb, regiaoParaiba);
        
        regiaoPb.setSigla("DF");
        
        assertEquals(regiaoPb, regiaoDf);
        assertEquals(regiaoPb.hashCode(), regiaoDf.hashCode());
        
       
    }
    
    @Test
    public void testToStringRegiao() {
        Regiao regiao = new Regiao("EUA");
        assertEquals("Regiao [sigla=EUA]", regiao.toString());
    }
    
    
}
