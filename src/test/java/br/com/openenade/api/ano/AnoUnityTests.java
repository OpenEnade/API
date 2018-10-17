package br.com.openenade.api.ano;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.ano.AnoRepository;
import br.com.openenade.api.ano.AnoService;

@DataJpaTest
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnoUnityTests {

    @Autowired
    private AnoRepository repository;

    @Autowired
    private AnoService service;

    @Test
    public void addAnoTest() {

        this.repository.deleteAll();

        Ano ano = new Ano();

        ano.setAno(2016);

        this.service.addAno(ano);
        assertEquals(ano, service.getAno(2016));
    }


    @Test
    public void addAnosTests() {

        this.repository.deleteAll();

        Ano ano1 = new Ano();
        Ano ano2 = new Ano();

        ano1.setAno(2016);
        ano2.setAno(2017);

        List<Ano> list = new ArrayList<>();

        list.add(ano1);
        list.add(ano2);

        this.service.addAno(ano1);
        this.service.addAno(ano2);

        assertEquals(list, service.getAllAnos());

    }

    @Test
    public void deleteAnoTest() {

        this.repository.deleteAll();

        Ano ano1 = new Ano();

        ano1.setAno(2016);

        this.service.addAno(ano1);

        assertEquals(ano1, service.getAno(2016));

        this.service.deleteAno(2016);

        assertEquals(null, service.getAno(2016));

    }

}
