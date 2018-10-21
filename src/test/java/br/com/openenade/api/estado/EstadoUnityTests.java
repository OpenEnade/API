package br.com.openenade.api.estado;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.regiao.Regiao;
import br.com.openenade.api.regiao.RegiaoService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EstadoUnityTests {

    @Autowired
    private EstadoRepository repository;
    
    @Autowired
    private RegiaoService rService;

    @Autowired
    private EstadoService service;

    @Test
    public void addEstadoTest() {

        this.repository.deleteAll();

        Regiao regiao = new Regiao("NE");
        
        this.rService.save(regiao);
        
        Estado estado = new Estado();
        
        estado.setRegiao(regiao);
        estado.setSigla("PB");

        this.service.addEstado(estado);
        assertEquals(estado, service.getEstado("PB"));
    }


    @Test
    public void addEstadosTests() {

        this.repository.deleteAll();
        Regiao regiao = new Regiao("NE");
        
        this.rService.save(regiao);
        
        Estado estado1 = new Estado();
        Estado estado2 = new Estado();

        
        estado1.setRegiao(regiao);
        estado1.setSigla("PB");
        
        estado2.setRegiao(regiao);
        estado2.setSigla("RN");
        
        List<Estado> list = new ArrayList<Estado>();
        
        list.add(estado1);
        list.add(estado2);
        
        this.service.addEstado(estado1);
        this.service.addEstado(estado2);

        assertEquals(list, service.getAllEstados());

    }

    @Test
    public void deleteEstadoTest() {

        this.repository.deleteAll();

        Regiao regiao = new Regiao("NE");
        
        this.rService.save(regiao);
        
        Estado estado = new Estado();
        
        estado.setRegiao(regiao);
        estado.setSigla("PB");

        this.service.addEstado(estado);

        assertEquals(estado, service.getEstado("PB"));

        this.service.deleteAno("PB");

        assertEquals(null, service.getEstado("PB"));

    }

}
