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
import br.com.openenade.api.BaseUnitTest;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.modalidade.Modalidade;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.regiao.Regiao;
import br.com.openenade.api.universidade.Universidade;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NotaServiceTests extends BaseUnitTest {

    @Autowired
    private NotaService notaService;

    @Test
    public void save() {
        Ano ano = new Ano();
        ano.setAno(2018);
        Regiao regiao = new Regiao("NO");
        Estado estado = new Estado("XD", regiao);
        Municipio municipio = new Municipio(123L, estado, "Capoeira Grande");
        Curso curso =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_PRESENCIAL);
        Universidade universidade = new Universidade(123123L, "UFCG", municipio,
                CategoriaAdmin.PUBLICO, new HashSet<>());
        universidade.getCursos().add(curso);

        Nota nota = new Nota.Builder().setAno(ano).setCurso(curso).setUniversidade(universidade)
                .build();
        nota.getAvaliacao().setConcluintesInscritos(33);
        nota.getAvaliacao().setConcluintesParticipantes(20);
        nota.getAvaliacao().setNotaBrutaCE(2.2);
        nota.getAvaliacao().setNotaBrutaFG(1.1);
        nota.getAvaliacao().setEnadeContinuo(3.333);
        nota.getAvaliacao().setEnadeFaixa(3);

        this.notaService.save(nota);

        assertTrue(this.notaService.getNotaById(nota.getInfo()).isPresent());

        this.notaService.save(nota);

        assertTrue(this.notaService.getNotaById(nota.getInfo()).isPresent());
    }
    
    public void saveUnivDuplicated() {
        Ano ano = new Ano();
        ano.setAno(2018);
        Regiao regiao = new Regiao("NO");
        Estado estado = new Estado("XD", regiao);
        Municipio municipio = new Municipio(123L, estado, "Capoeira Grande");
        Curso cursoA =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_PRESENCIAL);
        Curso cursoB =
                new Curso("Ciência da Neurologia", 41L, 1112223L, Modalidade.EDUCACAO_PRESENCIAL);
        
        
        Universidade universidadeA = new Universidade(123123L, "UFCG", municipio,
                CategoriaAdmin.PUBLICO, new HashSet<>());
        universidadeA.getCursos().add(cursoA);
        
        Universidade universidadeB = new Universidade(123123L, "UFCG", municipio,
                CategoriaAdmin.PUBLICO, new HashSet<>());
        universidadeA.getCursos().add(cursoB);

        Nota nota = new Nota.Builder().setAno(ano).setCurso(cursoA).setUniversidade(universidadeA)
                .build();

        this.notaService.save(nota);
        
        nota = new Nota.Builder().setAno(ano).setCurso(cursoB).setUniversidade(universidadeB).build();
        
        this.notaService.save(nota);

        assertTrue(this.notaService.getNotaById(nota.getInfo()).isPresent());
        
        nota = this.notaService.getNotaById(nota.getInfo()).get();
        
        assertEquals(2, nota.getInfo().getUniversidade().getCursos().size());
        
        assertTrue(nota.getInfo().getUniversidade().getCursos().contains(cursoA));
        assertTrue(nota.getInfo().getUniversidade().getCursos().contains(cursoB));
    }

    @Test
    public void getAll() {
        Ano ano = new Ano();
        ano.setAno(2017);
        Regiao regiao = new Regiao("NE");
        Estado estado = new Estado("PB", regiao);
        Municipio municipio = new Municipio(123L, estado, "Campina Grande");
        Curso curso =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_PRESENCIAL);
        Universidade universidade = new Universidade(123123L, "UFCG", municipio,
                CategoriaAdmin.PUBLICO, new HashSet<>());
        universidade.getCursos().add(curso);

        Nota nota3 = new Nota.Builder().setAno(ano).setCurso(curso).setUniversidade(universidade)
                .build();

        nota3 = this.notaService.save(nota3);

        ano = new Ano();
        ano.setAno(2017);
        regiao = new Regiao("N");
        estado = new Estado("AM", regiao);
        municipio = new Municipio(333L, estado, "Leruado");
        curso = new Curso("Engenharia dos Danones", 42L, 2334234L, Modalidade.EDUCACAO_PRESENCIAL);
        universidade = new Universidade(123122L, "UFAM", municipio, CategoriaAdmin.PUBLICO,
                new HashSet<>());
        universidade.getCursos().add(curso);

        Nota nota4 = new Nota.Builder().setAno(ano).setCurso(curso).setUniversidade(universidade)
                .build();

        nota4 = this.notaService.save(nota4);

        List<Nota> notas = this.notaService.getAll();
        assertTrue(notas.contains(nota3));
        assertTrue(notas.contains(nota4));
    }

    @Test
    public void getById() {
        Ano ano = new Ano();
        ano.setAno(2049);
        Regiao regiao = new Regiao("C");
        Estado estado = new Estado("Ancapistão", regiao);
        Municipio municipio = new Municipio(123L, estado, "Paulo Kogos");
        Curso curso =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_A_DISTANCIA);
        Universidade universidade = new Universidade(123123L, "UCIP", municipio,
                CategoriaAdmin.PRIVADO, new HashSet<>());
        universidade.getCursos().add(curso);

        Nota nota3 = new Nota.Builder().setAno(ano).setCurso(curso).setUniversidade(universidade)
                .build();

        nota3 = this.notaService.save(nota3);

        Optional<Nota> optNota = this.notaService.getNotaById(nota3.getInfo());

        assertTrue(optNota.isPresent());

        Nota nota = optNota.get();
        assertEquals(nota3, nota);

        assertEquals(nota3.hashCode(), nota.hashCode());

        assertEquals(ano, nota.getInfo().getAno());
        assertEquals(curso, nota.getInfo().getCurso());
        assertEquals(universidade, nota.getInfo().getUniversidade());
    }

    @Test
    public void deleteById() {
        Ano ano = new Ano();
        ano.setAno(2019);
        Regiao regiao = new Regiao("NE");
        Estado estado = new Estado("PE", regiao);
        Municipio municipio = new Municipio(123L, estado, "Campina Grande");
        Curso curso =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_PRESENCIAL);
        Universidade universidade = new Universidade(123123L, "UFCG", municipio,
                CategoriaAdmin.PUBLICO, new HashSet<>());
        universidade.getCursos().add(curso);

        Nota nota3 = new Nota.Builder().setAno(ano).setCurso(curso).setUniversidade(universidade)
                .build();

        nota3 = this.notaService.save(nota3);

        this.notaService.deleteNotaById(nota3.getInfo());
        
        assertFalse(this.notaService.getNotaById(nota3.getInfo()).isPresent());
    }

}
