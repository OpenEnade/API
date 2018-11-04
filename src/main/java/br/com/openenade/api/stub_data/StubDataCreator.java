package br.com.openenade.api.stub_data;

import br.com.openenade.api.ano.Ano;
import br.com.openenade.api.ano.AnoRepository;
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
import org.springframework.boot.ApplicationArguments;
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

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.addLines();
    }

    public void addLines() {

        String[] regioesStr = new String[] {"CO", "NE", "CO", "SE", "SE", "SE", "CO", "S", "S"};

        String[] estadosStr = new String[] {"MT", "MA", "GO", "SP", "SP", "SP", "GO", "PR", "RS"};

        String[] municipiosNamesStr = new String[] {"Cuiabá", "São Luís", "Anápolis",
                "Taquaritinga", "São José dos Campos", "Campinas", "Jussara", "Cornélio Procópio",
                "Bento Gonçalves"};

        Long[] municipiosCodes = new Long[] {5103403L, 2111300L, 5201108L, 3553708L, 3549904L,
                3509502L, 5212204L, 4106407L, 4302105L};

        Long[] cursoCodes = new Long[] {44L, 11891L, 1059394L, 18277L, 1268552L, 79804L, 21884L,
                1127676L, 106759L};

        Long[] cursoAreaCodes = new Long[] {21L, 21L, 21L, 72L, 76L, 79L, 702L, 702L, 904L};

        String[] cursoNames = new String[] {"ARQUITETURA E URBANISMO", "ARQUITETURA E URBANISMO",
                "ARQUITETURA E URBANISMO", "TECNOLOGIA EM ANÁLISE E DESENVOLVIMENTO DE SISTEMAS",
                "TECNOLOGIA EM GESTÃO DA PRODUÇÃO INDUSTRIAL",
                "TECNOLOGIA EM REDES DE COMPUTADORES", "MATEMÁTICA (LICENCIATURA)",
                "MATEMÁTICA (LICENCIATURA)", "LETRAS-PORTUGUÊS (LICENCIATURA)"};

        Modalidade[] modalidades = new Modalidade[] {Modalidade.EDUCACAO_PRESENCIAL,
                Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL,
                Modalidade.EDUCACAO_PRESENCIAL, Modalidade.EDUCACAO_PRESENCIAL};

        Integer[] anos = new Integer[] {2017, 2017, 2017, 2017, 2017, 2017, 2017, 2017, 2017};

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
        }
    }

}
