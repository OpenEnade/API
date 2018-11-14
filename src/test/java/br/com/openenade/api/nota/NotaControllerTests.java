package br.com.openenade.api.nota;

import static org.junit.Assert.*;
import java.util.HashSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import br.com.openenade.api.BaseUnitTest;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.modalidade.Modalidade;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.municipio.MunicipioService;
import br.com.openenade.api.regiao.Regiao;
import br.com.openenade.api.universidade.Universidade;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class NotaControllerTests extends BaseUnitTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NotaService notaService;

    @Autowired
    private MunicipioService municipioService;

    @Test
    public void postTestBasic() throws Exception {
        Ano ano = new Ano();
        ano.setAno(2018);
        Regiao regiao = new Regiao("NO");
        Estado estado = new Estado("XD", regiao);
        Municipio municipio = new Municipio(123L, estado, "Capoeira Grande");
        this.municipioService.save(municipio);
        Curso curso =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_PRESENCIAL);
        Universidade universidade = new Universidade(123123L, "UFCG", municipio,
                CategoriaAdmin.PUBLICO, new HashSet<>());
        universidade.getCursos().add(curso);

        Nota nota = new Nota(ano.getAno(), curso.getCodigoCurso(), curso.getModalidade(),
                universidade.getCodigoIES(), municipio.getCodigo());
        nota.setConcluintesInscritos(33);
        nota.setConcluintesParticipantes(20);
        nota.setNotaBrutaCE(2.2);
        nota.setNotaBrutaFG(1.1);
        nota.setEnadeContinuo(3.333);
        nota.setEnadeFaixa(3);

        String url = "/" + NotaController.ENDPOINT;

        assertFalse(notaService.getAll().contains(nota));

        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(nota))).andExpect(status().isOk());

        assertTrue(notaService.getAll().contains(nota));
    }

}
