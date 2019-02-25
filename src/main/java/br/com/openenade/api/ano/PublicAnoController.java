package br.com.openenade.api.ano;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = PublicAnoController.ENDPOINT)
public class PublicAnoController {

    public static final String ENDPOINT = "/anos";
    public static final String ANO_ID = "ano";

    @Autowired
    private AnoService service;

    @GetMapping
    public Collection<Ano> getAnos() {
        return service.getAllAnos();
    }

    @GetMapping(path = "/{" + ANO_ID + "}")
    public Ano getAno(@PathVariable(name = ANO_ID) Integer ano) {
        return service.getAno(ano);
    }

}
