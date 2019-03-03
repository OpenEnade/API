package br.com.openenade.api.nota;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = PublicNotaController.ENDPOINT)
public class PublicNotaController {

    public static final String ENDPOINT = "notas";

    @Autowired
    private NotaService service;

    @ResponseBody
    @GetMapping(path = "/{ano}-{codigoArea}-{modalidade}-{codigoIES}-{codigoMunicipio}")
    public Nota getNotaByIndex(NotaIdInterface notaIdInterface) {
        return this.service.getNota(notaIdInterface);
    }

    @GetMapping
    @ResponseBody
    public Collection<Nota> getFilteredNotas(NotaFilterInterface notaFilterInterface) {
        return this.service.filterByGenericAttribute(notaFilterInterface);
    }

}
