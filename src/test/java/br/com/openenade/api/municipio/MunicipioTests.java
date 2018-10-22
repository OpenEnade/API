package br.com.openenade.api.municipio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.regiao.Regiao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MunicipioTests {
    
    @Test
    public void testEstado() {
        Regiao regiao = new Regiao("OO");
        Estado estado = new Estado("EE", regiao);
        Municipio municipio = new Municipio((long) 10, estado, null);
        
        assertNull(municipio.getNome());
    }

    @Test
    public void testEqualsEstado() {
        Regiao regiaoPb = new Regiao("ND");
        
        Estado pe = new Estado("PE", regiaoPb);
        Estado ce = new Estado("CE", regiaoPb);
        
        Municipio coxi = new Municipio((long) 10, pe, "Coxixola");
        Municipio caba = new Municipio((long) 10, ce, "Cabaceiras");
        Municipio souz = new Municipio((long) 10, ce, "Souza");
        Municipio souzRepetido = new Municipio((long) 10, ce, "Souza");
        
        assertNotEquals(coxi, null);
        assertNotEquals(coxi, caba);
        
        assertEquals(souz.hashCode(), souzRepetido.hashCode());
        assertEquals(souz, souzRepetido);
        
        souz.setNome("Cabaceiras");
        
        assertEquals(caba, souz);
        assertEquals(caba.hashCode(), souz.hashCode());
        
       
    }
    
    @Test
    public void testToStringEstado() {
        Regiao regiao = new Regiao("EUA");
        Estado estado = new Estado("CA", regiao);
        Municipio coxi = new Municipio((long) 10, estado, "Coxixola");
        assertEquals("Municipio [codigo=10, estado=Estado [siglaEstado=CA,"
                + " regiaoEstado=Regiao [sigla=EUA]], nome=Coxixola]", coxi.toString());
    }
    

}
