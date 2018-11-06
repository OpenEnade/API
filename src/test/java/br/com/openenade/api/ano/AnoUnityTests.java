package br.com.openenade.api.ano;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.BaseUnitTest;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.ano.AnoService;
import br.com.openenade.api.exceptions.ResourceNotFound;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnoUnityTests extends BaseUnitTest {

    @Autowired
    private AnoService service;
    
    @Test
    public void addAnoTest() {
        Ano ano = new Ano();

        ano.setAno(2016);

        this.service.addAno(ano);
        assertEquals(ano, service.getAno(2016));
    }


    @Test
    public void addAnosTests() {
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

    @Test(expected = ResourceNotFound.class)
    public void deleteAnoTest() {
        Ano ano1 = new Ano();

        ano1.setAno(2016);

        this.service.addAno(ano1);

        assertEquals(ano1, service.getAno(2016));

        this.service.deleteAno(2016);

        this.service.getAno(2016);
    }

}
