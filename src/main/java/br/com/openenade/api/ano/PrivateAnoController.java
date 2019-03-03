package br.com.openenade.api.ano;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = PublicAnoController.ENDPOINT)
public class PrivateAnoController {

    @Autowired
    private AnoService service;

    @PostMapping
    public Ano createAno(@RequestBody Ano ano) {
        return this.service.addAno(ano);
    }

    @DeleteMapping(path = "/{" + PublicAnoController.ANO_ID + "}")
    public void deleteAno(@PathVariable(name = PublicAnoController.ANO_ID) Integer ano) {
        this.service.deleteAno(ano);
    }
}
