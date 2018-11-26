package br.com.openenade.api.nota;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
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
import br.com.openenade.api.municipio.MunicipioService;
import br.com.openenade.api.regiao.Regiao;
import br.com.openenade.api.universidade.Universidade;

@SpringBootTest
@RunWith(SpringRunner.class)
public class NotaServiceTests extends BaseUnitTest {

    @Autowired
    private NotaService notaService;

    @Autowired
    private MunicipioService municipioService;

    private List<Nota> notas;

    @After
    public void deleteAll() {

        this.notaService.deleteAll();
    }

    @Before
    public void setUp() {

        this.notas = this.addAnos();
    }

    public List<Nota> addAnos() {

        Ano ano1 = new Ano();
        ano1.setAno(2017);
        Regiao regiao1 = new Regiao("NE");
        Estado estado1 = new Estado("PE", regiao1);
        Municipio municipio1 = new Municipio(333L, estado1, "Recife");
        this.municipioService.save(municipio1);
        Curso curso1 =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_PRESENCIAL);
        Universidade universidade1 = new Universidade(11111L, "UFPE", municipio1,
                CategoriaAdmin.PUBLICO, new HashSet<>());
        universidade1.getCursos().add(curso1);

        Nota nota1 = new Nota.Builder().setAno(ano1).setCurso(curso1).setUniversidade(universidade1)
                .build();
        
        
        nota1.getAvaliacao().setConcluintesInscritos(50);
        nota1.getAvaliacao().setConcluintesParticipantes(20);
        nota1.getAvaliacao().setNotaBrutaCE(2.5);
        nota1.getAvaliacao().setNotaBrutaFG(1.7);
        nota1.getAvaliacao().setEnadeContinuo(3.553);
        nota1.getAvaliacao().setEnadeFaixa(3);


        Ano ano2 = new Ano();
        ano2.setAno(2018);
        Regiao regiao2 = new Regiao("CO");
        Estado estado2 = new Estado("MG", regiao2);
        Municipio municipio2 = new Municipio(222L, estado2, "Minas Gerais");
        this.municipioService.save(municipio2);
        Curso curso2 =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_PRESENCIAL);
        Universidade universidade2 = new Universidade(11111L, "UFMG", municipio2,
                CategoriaAdmin.PUBLICO, new HashSet<>());
        universidade2.getCursos().add(curso2);

        Nota nota2 = new Nota.Builder().setAno(ano2).setCurso(curso2).setUniversidade(universidade2)
                .build();
        
        nota2.getAvaliacao().setConcluintesInscritos(60);
        nota2.getAvaliacao().setConcluintesParticipantes(60);
        nota2.getAvaliacao().setNotaBrutaCE(2.9);
        nota2.getAvaliacao().setNotaBrutaFG(1.9);
        nota2.getAvaliacao().setEnadeContinuo(3.551);
        nota2.getAvaliacao().setEnadeFaixa(4);

        Ano ano = new Ano();
        ano.setAno(2019);
        Regiao regiao = new Regiao("NE");
        Estado estado = new Estado("PB", regiao);
        Municipio municipio = new Municipio(123L, estado, "Campina Grande");
        this.municipioService.save(municipio);
        Curso curso =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_PRESENCIAL);
        Universidade universidade = new Universidade(123123L, "UFCG", municipio,
                CategoriaAdmin.PRIVADO, new HashSet<>());
        universidade.getCursos().add(curso);

        Nota nota3 = new Nota.Builder().setAno(ano).setCurso(curso).setUniversidade(universidade)
                .build();

        nota3.getAvaliacao().setConcluintesInscritos(14);
        nota3.getAvaliacao().setConcluintesParticipantes(14);
        nota3.getAvaliacao().setNotaBrutaCE(2.5);
        nota3.getAvaliacao().setNotaBrutaFG(1.7);
        nota3.getAvaliacao().setEnadeContinuo(3.553);
        nota3.getAvaliacao().setEnadeFaixa(3);
        
        List<Nota> notas = new ArrayList<>();

        notas.add(nota1);
        notas.add(nota2);
        notas.add(nota3);

        this.notaService.save(nota1);
        this.notaService.save(nota2);
        this.notaService.save(nota3);

        return notas;
    }

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

        nota = new Nota.Builder().setAno(ano).setCurso(cursoB).setUniversidade(universidadeB)
                .build();

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


    @Test
    public void testFilterByAno() {

        NotaFilterInterface nfi = new NotaFilterInterface();
        nfi.setBeginAno(2017);
        nfi.setEndAno(2017);
        assertEquals(this.notas.get(0), this.notaService.filterByGenericAtribute(nfi).get(0));
    }

    @Test
    public void testFilterByCategoria() {

        List<Nota> publica = new ArrayList<>();

        publica.add(this.notas.get(0));
        publica.add(this.notas.get(1));

        NotaFilterInterface nfi = new NotaFilterInterface();
        nfi.setCategoria(CategoriaAdmin.PUBLICO);
        assertEquals(publica, this.notaService.filterByGenericAtribute(nfi));
    }

    @Test
    public void testFilterByCodigoCurso() {

        NotaFilterInterface nfi = new NotaFilterInterface();
        nfi.setCurso(2234234L);
        assertEquals(this.notas, this.notaService.filterByGenericAtribute(nfi));
    }

    @Test
    public void testFilterByEstado() {

        NotaFilterInterface nfi = new NotaFilterInterface();
        nfi.setEstado("PB");
        assertEquals(this.notas.get(2), this.notaService.filterByGenericAtribute(nfi).get(0));
    }

    @Test
    public void testFilterByModalidade() {

        NotaFilterInterface nfi = new NotaFilterInterface();
        nfi.setModalidade(Modalidade.EDUCACAO_PRESENCIAL);
        assertEquals(this.notas, this.notaService.filterByGenericAtribute(nfi));
    }

    @Test
    public void testFilterByMunicipio() {

        NotaFilterInterface nfi = new NotaFilterInterface();
        nfi.setMunicipio(123L);
        assertEquals(this.notas.get(2), this.notaService.filterByGenericAtribute(nfi).get(0));
    }

    @Test
    public void testFilterByRegiao() {
        List<Nota> nordeste = new ArrayList<>();

        nordeste.add(this.notas.get(0));
        nordeste.add(this.notas.get(2));

        NotaFilterInterface nfi = new NotaFilterInterface();
        nfi.setRegiao("NE");
        assertEquals(nordeste, this.notaService.filterByGenericAtribute(nfi));
    }

    @Test
    public void testFilterByCodigoIES() {

        List<Nota> byIES = new ArrayList<>();

        byIES.add(this.notas.get(0));
        byIES.add(this.notas.get(1));

        NotaFilterInterface nfi = new NotaFilterInterface();
        nfi.setUniversidade(11111L);
        assertEquals(byIES, this.notaService.filterByGenericAtribute(nfi));
    }

    @Test
    public void testFilterByNothing() {
        NotaFilterInterface nfi = new NotaFilterInterface();
        assertEquals(this.notas, this.notaService.filterByGenericAtribute(nfi));
    }

    @Test
    public void testFilterByAnoInterval() {

        List<Nota> anos = new ArrayList<>();
        anos.add(this.notas.get(0));
        anos.add(this.notas.get(1));

        NotaFilterInterface nfi = new NotaFilterInterface();
        nfi.setBeginAno(2017);
        nfi.setEndAno(2018);
        assertEquals(anos, this.notaService.filterByGenericAtribute(nfi));

        nfi.setEndAno(2019);
        anos.add(this.notas.get(2));
        assertEquals(anos, this.notaService.filterByGenericAtribute(nfi));
    }
    
    @Test
    public void caso1() {
            
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
  
        assertTrue(this.notaService.getAll().contains(nota));
   
    }
    
    @Test
    public void caso2() {
        
        Ano ano1 = new Ano();
        ano1.setAno(2017);
        Regiao regiao1 = new Regiao("NE");
        Estado estado1 = new Estado("PE", regiao1);
        Municipio municipio1 = new Municipio(333L, estado1, "Recife");
        this.municipioService.save(municipio1);
        Curso curso1 =
                new Curso("Ciência da Computação", 41L, 2234234L, Modalidade.EDUCACAO_PRESENCIAL);
        Universidade universidade1 = new Universidade(11111L, "UFPE", municipio1,
                CategoriaAdmin.PUBLICO, new HashSet<>());
        universidade1.getCursos().add(curso1);

        Nota nota1 = new Nota.Builder().setAno(ano1).setCurso(curso1).setUniversidade(universidade1)
                .build();
        
        nota1.getAvaliacao().setConcluintesInscritos(50);
        nota1.getAvaliacao().setConcluintesParticipantes(20);
        nota1.getAvaliacao().setNotaBrutaCE(2.5);
        nota1.getAvaliacao().setNotaBrutaFG(1.7);
        nota1.getAvaliacao().setEnadeContinuo(3.553);
        nota1.getAvaliacao().setEnadeFaixa(3);
        
        this.notaService.save(nota1);
       
        assertTrue(this.notaService.getAll().contains(this.notas.get(0)));
              
    } 
}
