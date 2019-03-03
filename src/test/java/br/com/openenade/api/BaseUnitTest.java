package br.com.openenade.api;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.ano.AnoService;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.curso.CursoId;
import br.com.openenade.api.curso.CursoService;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.estado.EstadoService;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.municipio.MunicipioService;
import br.com.openenade.api.nota.Nota;
import br.com.openenade.api.nota.NotaService;
import br.com.openenade.api.regiao.Regiao;
import br.com.openenade.api.regiao.RegiaoService;
import br.com.openenade.api.universidade.Universidade;
import br.com.openenade.api.universidade.UniversidadeService;

public class BaseUnitTest {

    public static final String BASE_URI = "/api";
    
    @Autowired
    private AnoService anoService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private RegiaoService regiaoService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private UniversidadeService universidadeService;

    @Autowired
    private NotaService notaService;

    /**
     * Do not change the order of deletions, if you want to add any other service, please append the
     * operation.
     */
    @Before
    @After
    public void clean() {
        for (Nota nota : this.notaService.getAll()) {
            this.notaService.deleteNotaById(nota.getInfo());
        }
        for (Universidade universidade : this.universidadeService.getAll()) {
            this.universidadeService.deleteUniversidadeById(universidade.getCodigoIES(),
                    universidade.getCampus());
        }
        for (Curso curso : this.cursoService.getAll()) {

            CursoId id = new CursoId(curso.getCodigoArea(), curso.getModalidade());
            this.cursoService.deleteCursoById(id);
        }
        for (Ano ano : this.anoService.getAllAnos()) {
            this.anoService.deleteAno(ano.getAno());
        }
        for (Regiao regiao : this.regiaoService.getAll()) {
            this.regiaoService.deleteRegiaoBySigla(regiao.getSigla());
        }
        for (Estado estado : this.estadoService.getAll()) {
            this.estadoService.deleteEstadoById(estado.getSigla());
        }
        for (Municipio municipio : this.municipioService.getAll()) {
            this.municipioService.deleteMunicipioByCodigo(municipio.getCodigo());
        }
    }
}
