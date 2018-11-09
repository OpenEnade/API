package br.com.openenade.api.nota;

import static org.junit.Assert.*;
import java.util.HashSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.modalidade.Modalidade;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.municipio.MunicipioService;
import br.com.openenade.api.regiao.Regiao;
import br.com.openenade.api.universidade.Universidade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotaServiceTests {

    @Autowired
    private NotaService notaService;
    
    @Autowired
    private MunicipioService municipioService;

    @Test
    public void save() {
        Ano ano = new Ano();
        ano.setAno(2017);
        Regiao regiao = new Regiao("NE");
        Estado estado = new Estado("PB", regiao);
        Municipio municipio = new Municipio(123L, estado, "Campina Grande");
        this.municipioService.save(municipio);
        Curso curso =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_PRESENCIAL);
        Universidade universidade = new Universidade(123123L, "UFCG", municipio,
                CategoriaAdmin.PUBLICO, new HashSet<>());
        universidade.getCursos().add(curso);

        Nota nota = new Nota(3, ano, curso, universidade, 60, 44, 0.5, 0.5, 0.5, 0.5, 0.5, 1);

        this.notaService.save(nota);
    }

}
