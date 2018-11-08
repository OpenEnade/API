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
import br.com.openenade.api.regiao.Regiao;
import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import br.com.openenade.api.regiao.RegiaoRepository;
import br.com.openenade.api.universidade.Universidade;
import br.com.openenade.api.universidade.UniversidadeRepository;
import org.springframework.boot.ApplicationArguments;
import java.util.ArrayList;
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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.addLines();
    }

    public void addLines() {

        String[] regioesStr = getRegioesStr();

        String[] estadosStr = getEstadosStr();

        String[] municipiosNamesStr = getMunicipiosStr();

        Long[] municipiosCodes = getMunicipiosCodes();

        Long[] codesIES = getCodesIES();

        String[] namesIES = getNamesIES();

        CategoriaAdmin[] categoriasAdm = new CategoriaAdmin[] {CategoriaAdmin.PUBLICO,
                CategoriaAdmin.PRIVADO, CategoriaAdmin.PUBLICO, CategoriaAdmin.PRIVADO,
                CategoriaAdmin.PRIVADO, CategoriaAdmin.PRIVADO, CategoriaAdmin.PRIVADO,
                CategoriaAdmin.PRIVADO, CategoriaAdmin.PRIVADO, CategoriaAdmin.PUBLICO,
                CategoriaAdmin.PUBLICO, CategoriaAdmin.PUBLICO, CategoriaAdmin.PUBLICO,
                CategoriaAdmin.PUBLICO, CategoriaAdmin.PUBLICO, CategoriaAdmin.PUBLICO,
                CategoriaAdmin.PRIVADO, CategoriaAdmin.PRIVADO};
        
        Long[] cursoCodes = new Long[] {44L, 1059394L, 18277L, 1186931L, 1153739L, 5001211L, 79804L,
                119946L, 101378L, 21884L, 21892L, 21897L, 45102L, 47333L, 1127676L, 1128169L,
                106759L, 40720L};

        Long[] cursoAreaCodes = new Long[] {21L, 21L, 72L, 72L, 72L, 72L, 79L, 79L, 79L, 702L, 702L,
                702L, 702L, 702L, 702L, 702L, 904L, 904L};

        String[] cursoNames = new String[] {"ARQUITETURA E URBANISMO", "ARQUITETURA E URBANISMO",
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

        Modalidade[] modalidades =
                new Modalidade[] {Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                        Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                        Modalidade.EDUCACAO_A_DISTANCIA, Modalidade.EDUCACAO_PRESENCIAL,
                        Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                        Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                        Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                        Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                        Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                        Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL};

        Integer[] anos = new Integer[] {2017, 2017, 2017, 2017, 2017, 2017, 2017, 2017, 2017, 2017,
                2017, 2017, 2017, 2017, 2017, 2017, 2017, 2017};

        for (int i = 0; i < regioesStr.length; i++) {
            Regiao regiao = new Regiao(regioesStr[i]);
            this.regiaoRepository.save(regiao);

            Estado estado = new Estado(estadosStr[i], regiao);
            this.estadoRepository.save(estado);

            Municipio municipio = new Municipio(municipiosCodes[i], estado, municipiosNamesStr[i]);
            this.municipioRepository.save(municipio);

            Curso curso =
                    new Curso(cursoNames[i], cursoAreaCodes[i], cursoCodes[i], modalidades[i]);
            this.cursoRepository.save(curso);

            Ano ano = new Ano();
            ano.setAno(anos[i]);
            this.anoRepository.save(ano);

            Universidade universidade = null;
            if (this.universidadeRepository.existsById(codesIES[i])) {
                universidade = this.universidadeRepository.findById(codesIES[i]).get();
            } else {
                universidade = new Universidade(codesIES[i], namesIES[i], municipio,
                        categoriasAdm[i], new ArrayList<>());
            }
            universidade.getCursos().add(curso);
            this.universidadeRepository.save(universidade);
        }
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
        return new Long[] {1L, 11544L, 967L, 997L, 1041L, 3295L, 3456L, 3543L, 3613L,
                47L, 47L, 47L, 47L, 47L, 588L, 588L, 13L, 14L};
    }

    private Long[] getMunicipiosCodes() {
        return new Long[] {5103403L, 5201108L, 3553708L, 3506003L, 4311403L,
                4205407L, 3509502L, 4313409L, 5208004L, 5212204L, 5218300L, 5219308L, 5218003L,
                5210208L, 4106407L, 4106902L, 4302105L, 4318705L};
    }

    private String[] getMunicipiosStr() {
        return new String[] {"Cuiabá", "Anápolis", "Taquaritinga", "Bauru",
                "Lajeado", "Florianópolis", "Campinas", "Novo Hamburgo", "Formosa", "Jussara",
                "Posse", "Santa Helena de Goiás", "Porangatu", "Iporá", "Cornélio Procópio",
                "Curitiba", "Bento Gonçalves", "São Leopoldo"};
    }

    private String[] getEstadosStr() {
        return new String[] {"MT", "GO", "SP", "SP", "RS", "SC", "SP", "RS", "GO",
                "GO", "GO", "GO", "GO", "GO", "PR", "PR", "RS", "RS"};
    }

    private String[] getRegioesStr() {
        return new String[] {"CO", "CO", "SE", "SE", "S", "S", "SE", "S", "CO", "CO",
                "CO", "CO", "CO", "CO", "S", "S", "S", "S"};
    }

}
