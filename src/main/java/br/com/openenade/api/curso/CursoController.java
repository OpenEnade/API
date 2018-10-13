package br.com.openenade.api.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping(path = CursoController.ENDPOINT)
public class CursoController {

    public static final String ENDPOINT = "curso";

    @Autowired
    private CursoService service;

    @ResponseBody
    @GetMapping(path = "/{codigo}")
    public Curso getCursoByCodigo(@PathVariable(name = "codigo") Long codigo) {
        return this.service.getCursoByCodigo(codigo);
    }

}
