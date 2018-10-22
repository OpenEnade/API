package br.com.openenade.api.municipio;

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
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.regiao.Regiao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MunicipioServiceTests {

    @Autowired
    private MunicipioRepository repository;
    
    @Autowired
    private MunicipioService service;
    
    
    Regiao df = new Regiao("DF");
    
    Estado as = new Estado("AS", df);
    
    
    @Test
    public void saveTest() {
        Municipio municipio = new Municipio((long) 10, as, "belem");
        
        this.service.save(municipio);
        Municipio aux = this.service.getByCodigo((long) 10).get();
        assertEquals("belem", aux.getNome());
    }
    
    @Test
    public void getAllTest() {
        Regiao df = new Regiao("DF");
        Regiao sp = new Regiao("SP");
        

        Estado as = new Estado("AS", df);
        Estado es = new Estado("ES", sp);
        Estado te = new Estado("TE", sp);

        Municipio belem = new Municipio((long) 10, as, "belem");
        Municipio santos = new Municipio((long) 11, es, "santos");
        Municipio hellcife = new Municipio((long) 12, te, "hellcife");
        Municipio teste = new Municipio((long) 13, as, "teste");
        
        this.service.save(belem);
        this.service.save(santos);
        this.service.save(hellcife);
        
        List<Municipio> municipios = this.service.getAll();
        
        assertEquals(3, municipios.size());
        
        assertTrue(municipios.contains(belem));
        assertTrue(municipios.contains(santos));
        assertFalse(municipios.contains(teste));
        
    }
    
    @Test
    public void getByCodigoTest() {
        this.repository.deleteAll();
        
        Municipio municipio1 = new Municipio((long) 10, as, "belem");
        Municipio municipio2 = new Municipio((long) 11, as, "dopara");
        
        this.service.save(municipio1);
        this.service.save(municipio2);
        
        
        Municipio aux = this.service.getByCodigo((long) 10).get();
        
        assertEquals(aux, municipio1);
        
       
    }
    
    @Test
    public void deleteEstadoByCodigoTest() {
        this.repository.deleteAll();
        
        Municipio municipio1 = new Municipio((long) 10, as, "belem");
        
        this.service.save(municipio1);
        
        this.service.deleteMunicipioByCodigo((long) 10);
        
        assertEquals(Optional.empty(), this.service.getByCodigo((long) 10));
    }
}
