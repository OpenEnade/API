package br.com.openenade.api.estado;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.regiao.Regiao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstadoTests {

    @Test
    public void testEstado() {
        Regiao regiao = new Regiao("OO");
        Estado estado = new Estado(null, regiao);
        
        assertNull(estado.getSiglaEstado());
    }

    @Test
    public void testEqualsEstado() {
        Regiao regiaoPb = new Regiao("ND");
        
        Estado peRepetido = new Estado("PE", regiaoPb);
        Estado pe = new Estado("PE", regiaoPb);
        Estado ce = new Estado("CE", regiaoPb);
        Estado sp = new Estado("SP", new Regiao("SL"));
        
        assertNotEquals(pe, null);
        assertNotEquals(pe, ce);
        assertNotEquals(pe, sp);
        
        assertEquals(pe.hashCode(), peRepetido.hashCode());
        assertEquals(pe, peRepetido);
        
        ce.setSiglaEstado("PE");
        
        assertEquals(pe, ce);
        assertEquals(pe.hashCode(), ce.hashCode());
        
       
    }
    
    @Test
    public void testToStringEstado() {
        Regiao regiao = new Regiao("EUA");
        Estado estado = new Estado("CA", regiao);
        assertEquals("Estado [siglaEstado=CA, regiaoEstado=Regiao [sigla=EUA]]", estado.toString());
    }
    
}
