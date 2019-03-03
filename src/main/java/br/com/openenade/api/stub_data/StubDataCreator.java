package br.com.openenade.api.stub_data;

import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.ano.AnoRepository;
import br.com.openenade.api.categoriaadmin.CategoriaAdmin;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.curso.CursoRepository;
import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.estado.EstadoRepository;
import br.com.openenade.api.modalidade.Modalidade;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.municipio.MunicipioRepository;
import br.com.openenade.api.nota.Nota;
import br.com.openenade.api.nota.NotaService;
import br.com.openenade.api.regiao.Regiao;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import br.com.openenade.api.regiao.RegiaoRepository;
import br.com.openenade.api.universidade.Universidade;
import br.com.openenade.api.universidade.UniversidadeId;
import br.com.openenade.api.universidade.UniversidadeRepository;
import org.springframework.boot.ApplicationArguments;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class StubDataCreator implements ApplicationRunner {

    @Autowired
    private RegiaoRepository regiaoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private MunicipioRepository municipioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AnoRepository anoRepository;

    @Autowired
    private UniversidadeRepository universidadeRepository;

    @Autowired
    private NotaService notaService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (args.containsOption("stub-data")) {
            this.addLines();
        }
    }

    public void addLines() {

        Regiao[] regioes = this.addRegioes();

        Estado[] estados = this.addEstados(regioes);

        Municipio[] municipios = this.addMunicipios(estados);

        Curso[] cursos = this.addCursos();

        Ano anos[] = this.addAnos();

        Universidade universidades[] = this.addUniversidades(municipios, cursos);

        this.addNotas(anos, cursos, universidades);
    }

    private Regiao[] addRegioes() {
        String[] regioesStr = getRegioesStr();

        Regiao[] regioes = new Regiao[regioesStr.length];
        for (int i = 0; i < regioes.length; i++) {
            regioes[i] = new Regiao(regioesStr[i]);
            this.regiaoRepository.save(regioes[i]);
        }

        return regioes;
    }

    private Estado[] addEstados(Regiao[] regioes) {
        String[] estadosStr = getEstadosStr();

        Estado[] estados = new Estado[estadosStr.length];
        for (int i = 0; i < estados.length; i++) {
            estados[i] = new Estado(estadosStr[i], regioes[i]);
            this.estadoRepository.save(estados[i]);
        }

        return estados;
    }

    private Municipio[] addMunicipios(Estado[] estados) {
        Long[] municipiosCodes = getMunicipiosCodes();
        String[] municipiosNamesStr = getMunicipiosStr();

        Municipio[] municipios = new Municipio[municipiosCodes.length];
        for (int i = 0; i < municipios.length; i++) {
            municipios[i] = new Municipio(municipiosCodes[i], estados[i], municipiosNamesStr[i]);
            this.municipioRepository.save(municipios[i]);
        }

        return municipios;
    }

    private Curso[] addCursos() {
        Long[] cursoCodes = getCursoCodes();
        Long[] cursoAreaCodes = getCursoAreaCodes();
        String[] cursoNames = getCursoNames();
        Modalidade[] modalidades = getModalidades();

        Curso[] cursos = new Curso[cursoCodes.length];
        for (int i = 0; i < cursos.length; i++) {
            cursos[i] = new Curso(cursoNames[i], cursoAreaCodes[i], modalidades[i]);
            this.cursoRepository.save(cursos[i]);
        }

        return cursos;
    }

    private Ano[] addAnos() {
        Integer[] anosInt = getAnos();

        Ano[] anos = new Ano[anosInt.length];
        for (int i = 0; i < anos.length; i++) {
            anos[i] = new Ano(anosInt[i]);
            this.anoRepository.save(anos[i]);
        }

        return anos;
    }

    private Universidade[] addUniversidades(Municipio[] municipios, Curso[] cursos) {
        Long[] codesIES = getCodesIES();
        String[] namesIES = getNamesIES();
        CategoriaAdmin[] categoriasAdm = getCategoriasAdmin();

        Universidade[] universidades = new Universidade[codesIES.length];
        for (int i = 0; i < universidades.length; i++) {
            UniversidadeId universidadeId =
                    new UniversidadeId(codesIES[i], municipios[i].getCodigo());

            if (this.universidadeRepository.existsById(universidadeId)) {
                universidades[i] = this.universidadeRepository.findById(universidadeId).get();
            } else {
                universidades[i] = new Universidade(codesIES[i], namesIES[i], municipios[i],
                        categoriasAdm[i], new HashSet<>());
            }
            universidades[i].getCursos().add(cursos[i]);
            this.universidadeRepository.save(universidades[i]);
        }

        return universidades;
    }

    private void addNotas(Ano anos[], Curso cursos[], Universidade universidades[]) {
        for (int i = 0; i < anos.length; i++) {
            Nota nota = new Nota.Builder().setAno(anos[i]).setCurso(cursos[i])
                    .setUniversidade(universidades[i]).build();

            this.notaService.addNota(nota);
        }
    }

    private Integer[] getAnos() {
        return new Integer[] {2017, 2017, 2017, 2017, 2017, 2017, 2017, 2017, 2017, 2017, 2017,
                2017, 2017, 2017, 2017, 2017, 2017, 2017};
    }

    private Modalidade[] getModalidades() {
        return new Modalidade[] {Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                Modalidade.EDUCACAO_A_DISTANCIA, Modalidade.EDUCACAO_PRESENCIAL,
                Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL};
    }

    private String[] getCursoNames() {
        return new String[] {"ARQUITETURA E URBANISMO", "ARQUITETURA E URBANISMO",
                "TECNOLOGIA EM ANÁLISE E DESENVOLVIMENTO DE SISTEMAS",
                "TECNOLOGIA EM ANÁLISE E DESENVOLVIMENTO DE SISTEMAS",
                "TECNOLOGIA EM ANÁLISE E DESENVOLVIMENTO DE SISTEMAS",
                "TECNOLOGIA EM ANÁLISE E DESENVOLVIMENTO DE SISTEMAS",
                "TECNOLOGIA EM REDES DE COMPUTADORES", "TECNOLOGIA EM REDES DE COMPUTADORES",
                "TECNOLOGIA EM REDES DE COMPUTADORES", "MATEMÁTICA (LICENCIATURA)",
                "MATEMÁTICA (LICENCIATURA)", "MATEMÁTICA (LICENCIATURA)",
                "MATEMÁTICA (LICENCIATURA)", "MATEMÁTICA (LICENCIATURA)",
                "MATEMÁTICA (LICENCIATURA)", "MATEMÁTICA (LICENCIATURA)",
                "LETRAS-PORTUGUÊS (LICENCIATURA)", "LETRAS-PORTUGUÊS (LICENCIATURA)"};
    }

    private Long[] getCursoAreaCodes() {
        return new Long[] {21L, 21L, 72L, 72L, 72L, 72L, 79L, 79L, 79L, 702L, 702L, 702L, 702L,
                702L, 702L, 702L, 904L, 904L};
    }

    private Long[] getCursoCodes() {
        return new Long[] {44L, 1059394L, 18277L, 1186931L, 1153739L, 5001211L, 79804L, 119946L,
                101378L, 21884L, 21892L, 21897L, 45102L, 47333L, 1127676L, 1128169L, 106759L,
                40720L};
    }

    private CategoriaAdmin[] getCategoriasAdmin() {
        return new CategoriaAdmin[] {CategoriaAdmin.PUBLICO, CategoriaAdmin.PRIVADO,
                CategoriaAdmin.PUBLICO, CategoriaAdmin.PRIVADO, CategoriaAdmin.PRIVADO,
                CategoriaAdmin.PRIVADO, CategoriaAdmin.PRIVADO, CategoriaAdmin.PRIVADO,
                CategoriaAdmin.PRIVADO, CategoriaAdmin.PUBLICO, CategoriaAdmin.PUBLICO,
                CategoriaAdmin.PUBLICO, CategoriaAdmin.PUBLICO, CategoriaAdmin.PUBLICO,
                CategoriaAdmin.PUBLICO, CategoriaAdmin.PUBLICO, CategoriaAdmin.PRIVADO,
                CategoriaAdmin.PRIVADO};
    }

    private String[] getNamesIES() {
        return new String[] {"UNIVERSIDADE FEDERAL DE MATO GROSSO",
                "FACULDADE METROPOLITANA DE ANÁPOLIS", "FACULDADE DE TECNOLOGIA DE TAQUARITINGA",
                "Centro Universitário de Bauru", "UNIVERSIDADE DO VALE DO TAQUARI",
                "FACULDADE SENAC FLORIANÓPOLIS", "FACULDADE POLITÉCNICA DE CAMPINAS",
                "FACULDADE NOVO HAMBURGO", "FACULDADES INTEGRADAS IESGO",
                "UNIVERSIDADE ESTADUAL DE GOIÁS", "UNIVERSIDADE ESTADUAL DE GOIÁS",
                "UNIVERSIDADE ESTADUAL DE GOIÁS", "UNIVERSIDADE ESTADUAL DE GOIÁS",
                "UNIVERSIDADE ESTADUAL DE GOIÁS", "UNIVERSIDADE TECNOLÓGICA FEDERAL DO PARANÁ",
                "UNIVERSIDADE TECNOLÓGICA FEDERAL DO PARANÁ", "UNIVERSIDADE DE CAXIAS DO SUL",
                "UNIVERSIDADE DO VALE DO RIO DOS SINOS"};
    }

    private Long[] getCodesIES() {
        return new Long[] {1L, 11544L, 967L, 997L, 1041L, 3295L, 3456L, 3543L, 3613L, 47L, 47L, 47L,
                47L, 47L, 588L, 588L, 13L, 14L};
    }

    private Long[] getMunicipiosCodes() {
        return new Long[] {5103403L, 5201108L, 3553708L, 3506003L, 4311403L, 4205407L, 3509502L,
                4313409L, 5208004L, 5212204L, 5218300L, 5219308L, 5218003L, 5210208L, 4106407L,
                4106902L, 4302105L, 4318705L};
    }

    private String[] getMunicipiosStr() {
        return new String[] {"Cuiabá", "Anápolis", "Taquaritinga", "Bauru", "Lajeado",
                "Florianópolis", "Campinas", "Novo Hamburgo", "Formosa", "Jussara", "Posse",
                "Santa Helena de Goiás", "Porangatu", "Iporá", "Cornélio Procópio", "Curitiba",
                "Bento Gonçalves", "São Leopoldo"};
    }

    private String[] getEstadosStr() {
        return new String[] {"MT", "GO", "SP", "SP", "RS", "SC", "SP", "RS", "GO", "GO", "GO", "GO",
                "GO", "GO", "PR", "PR", "RS", "RS"};
    }

    private String[] getRegioesStr() {
        return new String[] {"CO", "CO", "SE", "SE", "S", "S", "SE", "S", "CO", "CO", "CO", "CO",
                "CO", "CO", "S", "S", "S", "S"};
    }

}
