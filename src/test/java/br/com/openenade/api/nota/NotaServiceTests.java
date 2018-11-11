package br.com.openenade.api.nota;

import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
        nota.setConcluintesInscritos(33);
        nota.setConcluintesParticipantes(20);
        nota.setNotaBrutaCE(2.2);
        nota.setNotaBrutaFG(1.1);
        nota.setEnadeContinuo(3.333);
        nota.setEnadeFaixa(3);

        this.notaService.save(nota);

        this.notaService.save(nota);

        assertTrue(this.notaService.getNotaByIndex(nota.getIndex()).isPresent());
    }

    @Test
    public void getAll() {
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

        Nota nota3 = new Nota(ano, curso, universidade);

        nota3 = this.notaService.save(nota3);

        ano = new Ano();
        ano.setAno(2017);
        regiao = new Regiao("N");
        estado = new Estado("AM", regiao);
        municipio = new Municipio(333L, estado, "Leruado");
        this.municipioService.save(municipio);
        curso = new Curso("Engenharia dos Danones", 42L, 2334234L, Modalidade.EDUCACAO_PRESENCIAL);
        universidade = new Universidade(123123L, "UFAM", municipio, CategoriaAdmin.PUBLICO,
                new HashSet<>());
        universidade.getCursos().add(curso);

        Nota nota4 = new Nota(ano, curso, universidade);

        nota4 = this.notaService.save(nota4);

        List<Nota> notas = this.notaService.getAll();
        assertTrue(notas.contains(nota3));
        assertTrue(notas.contains(nota4));
    }

    @Test
    public void getByIndex() {
        Ano ano = new Ano();
        ano.setAno(2049);
        Regiao regiao = new Regiao("C");
        Estado estado = new Estado("Ancapistão", regiao);
        Municipio municipio = new Municipio(123L, estado, "Paulo Kogos");
        this.municipioService.save(municipio);
        Curso curso =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_A_DISTANCIA);
        Universidade universidade = new Universidade(123123L, "UCIP", municipio,
                CategoriaAdmin.PRIVADO, new HashSet<>());
        universidade.getCursos().add(curso);

        Nota nota3 = new Nota(ano, curso, universidade);

        nota3 = this.notaService.save(nota3);

        Optional<Nota> optNota = this.notaService.getNotaByIndex(nota3.getIndex());

        assertTrue(optNota.isPresent());

        Nota nota = optNota.get();
        assertEquals(nota3, nota);

        assertEquals(nota3.hashCode(), nota.hashCode());

        assertEquals((Integer) 2049, nota.getAno().getAno());
        assertEquals(curso, nota.getCurso());
        assertEquals(municipio, nota.getUniversidade().getCampus());
    }

    @Test
    public void deleteByIndex() {
        Ano ano = new Ano();
        ano.setAno(2019);
        Regiao regiao = new Regiao("NE");
        Estado estado = new Estado("PE", regiao);
        Municipio municipio = new Municipio(123L, estado, "Campina Grande");
        this.municipioService.save(municipio);
        Curso curso =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_PRESENCIAL);
        Universidade universidade = new Universidade(123123L, "UFCG", municipio,
                CategoriaAdmin.PUBLICO, new HashSet<>());
        universidade.getCursos().add(curso);

        Nota nota3 = new Nota(ano, curso, universidade);

        nota3 = this.notaService.save(nota3);

        int index = nota3.getIndex();
        while (this.notaService.getNotaByIndex(index).isPresent()) {
            index++;
        }

        assertFalse(this.notaService.deleteNotaByIndex(index));

        assertTrue(this.notaService.deleteNotaByIndex(nota3.getIndex()));
    }

}
