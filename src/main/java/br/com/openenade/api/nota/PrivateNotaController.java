package br.com.openenade.api.nota;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = PublicNotaController.ENDPOINT)
public class PrivateNotaController {

    @Autowired
    private NotaService service;

    @PostMapping
    @ResponseBody
    public Nota postNota(@Valid @RequestBody Nota nota) {
        return this.service.addNota(nota);
    }

    @DeleteMapping(path = "/{ano}-{codigoArea}-{modalidade}-{codigoIES}-{codigoMunicipio}")
    public void deleteNotaByIndex(NotaIdInterface notaIdInterface) {
        this.service.deleteNota(notaIdInterface);
    }

}
