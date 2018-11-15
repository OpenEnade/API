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
import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

        Nota nota = new Nota(ano, curso, universidade);
        nota.getAvaliacao().setConcluintesInscritos(33);
        nota.getAvaliacao().setConcluintesParticipantes(20);
        nota.getAvaliacao().setNotaBrutaCE(2.2);
        nota.getAvaliacao().setNotaBrutaFG(1.1);
        nota.getAvaliacao().setEnadeContinuo(3.333);
        nota.getAvaliacao().setEnadeFaixa(3);

        String url = "/" + NotaController.ENDPOINT;

        assertFalse(notaService.getAll().contains(nota));

        mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(nota))).andExpect(status().isOk());

        assertTrue(notaService.getAll().contains(nota));
    }

    @Test
    public void getTestBasic() throws Exception {
        Ano ano = new Ano();
        ano.setAno(2018);
        Regiao regiao = new Regiao("NE");
        Estado estado = new Estado("GO", regiao);
        Municipio municipio = new Municipio(123L, estado, "Poeira Grande");
        this.municipioService.save(municipio);
        Curso curso =
                new Curso("Ciência da Computação", 33L, 9999L, Modalidade.EDUCACAO_PRESENCIAL);
        Universidade universidade = new Universidade(3213321L, "UFREI", municipio,
                CategoriaAdmin.PUBLICO, new HashSet<>());
        universidade.getCursos().add(curso);

        Nota nota = new Nota(ano, curso, universidade);
        nota.getAvaliacao().setConcluintesInscritos(3);
        nota.getAvaliacao().setConcluintesParticipantes(2);
        nota.getAvaliacao().setNotaBrutaCE(2.1);
        nota.getAvaliacao().setNotaBrutaFG(0.5);
        nota.getAvaliacao().setEnadeContinuo(3.666);
        nota.getAvaliacao().setEnadeFaixa(1);

        this.notaService.save(nota);

        String url = "/" + NotaController.ENDPOINT
                + String.format("/%d-%d-%s-%d-%d", nota.getInfo().getAno().getAno(),
                        nota.getInfo().getCurso().getCodigoCurso(),
                        nota.getInfo().getCurso().getModalidade(),
                        nota.getInfo().getUniversidade().getCodigoIES(),
                        nota.getInfo().getUniversidade().getCampus().getCodigo());

        MvcResult result =
                mvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        assertEquals(result.getResponse().getContentAsString(),
                "{\"info\":{\"ano\":{\"ano\":2018},\"curso\":{\"codigoCurso\":"
                        + "9999,\"nome\":\"Ciência da Computação\",\"codigoArea\":33,\""
                        + "modalidade\":\"Educação Presencial\"},\"universidade\":{\"c"
                        + "odigoIES\":3213321,\"nome\":\"UFREI\",\"campus\":{\"codigo"
                        + "\":123,\"estado\":{\"sigla\":\"GO\",\"regiao\":{\"sigla\":"
                        + "\"NE\"}},\"nome\":\"Poeira Grande\"},\"categoriaAdmin\":\"P"
                        + "ublico\",\"cursos\":[{\"codigoCurso\":9999,\"nome\":\"Ciênc"
                        + "ia da Computação\",\"codigoArea\":33,\"modalidade\":\"Educa"
                        + "ção Presencial\"}]}},\"avaliacao\":{\"concluintesInscritos"
                        + "\":3,\"concluintesParticipantes\":2,\"notaBrutaFG\":0.5,\""
                        + "notaPadronizadaFG\":0.0,\"notaBrutaCE\":2.1,\"notaPadroniz"
                        + "adaCE\":0.0,\"enadeContinuo\":3.666,\"enadeFaixa\":1}}");
    }

}
