package br.com.openenade.api.stub_data;

import br.com.openenade.api.estado.Estado;
import br.com.openenade.api.estado.EstadoRepository;
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
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.addLines();
    }
    
    public void addLines() {                
        Regiao regiao0 = new Regiao("CO");
        this.regiaoRepository.save(regiao0);
        Estado estado0 = new Estado("MT", regiao0);
        this.estadoRepository.save(estado0);
        Municipio municipio0 = new Municipio(5103403L, estado0, "Cuiabá");
        this.municipioRepository.save(municipio0);


        Regiao regiao1 = new Regiao("CO");
        this.regiaoRepository.save(regiao1);
        Estado estado1 = new Estado("DF", regiao1);
        this.estadoRepository.save(estado1);
        Municipio municipio1 = new Municipio(5300108L, estado1, "Brasília");
        this.municipioRepository.save(municipio1);


        Regiao regiao2 = new Regiao("CO");
        this.regiaoRepository.save(regiao2);
        Estado estado2 = new Estado("DF", regiao2);
        this.estadoRepository.save(estado2);
        Municipio municipio2 = new Municipio(5300108L, estado2, "Brasília");
        this.municipioRepository.save(municipio2);


        Regiao regiao3 = new Regiao("NE");
        this.regiaoRepository.save(regiao3);
        Estado estado3 = new Estado("SE", regiao3);
        this.estadoRepository.save(estado3);
        Municipio municipio3 = new Municipio(2803609L, estado3, "Laranjeiras");
        this.municipioRepository.save(municipio3);


        Regiao regiao4 = new Regiao("N");
        this.regiaoRepository.save(regiao4);
        Estado estado4 = new Estado("AM", regiao4);
        this.estadoRepository.save(estado4);
        Municipio municipio4 = new Municipio(1302603L, estado4, "Manaus");
        this.municipioRepository.save(municipio4);


        Regiao regiao5 = new Regiao("NE");
        this.regiaoRepository.save(regiao5);
        Estado estado5 = new Estado("PI", regiao5);
        this.estadoRepository.save(estado5);
        Municipio municipio5 = new Municipio(2211001L, estado5, "Teresina");
        this.municipioRepository.save(municipio5);


        Regiao regiao6 = new Regiao("SE");
        this.regiaoRepository.save(regiao6);
        Estado estado6 = new Estado("MG", regiao6);
        this.estadoRepository.save(estado6);
        Municipio municipio6 = new Municipio(3146107L, estado6, "Ouro Preto");
        this.municipioRepository.save(municipio6);


        Regiao regiao7 = new Regiao("SE");
        this.regiaoRepository.save(regiao7);
        Estado estado7 = new Estado("MG", regiao7);
        this.estadoRepository.save(estado7);
        Municipio municipio7 = new Municipio(3171303L, estado7, "Viçosa");
        this.municipioRepository.save(municipio7);


        Regiao regiao8 = new Regiao("S");
        this.regiaoRepository.save(regiao8);
        Estado estado8 = new Estado("PR", regiao8);
        this.estadoRepository.save(estado8);
        Municipio municipio8 = new Municipio(4113700L, estado8, "Londrina");
        this.municipioRepository.save(municipio8);


        Regiao regiao9 = new Regiao("S");
        this.regiaoRepository.save(regiao9);
        Estado estado9 = new Estado("PR", regiao9);
        this.estadoRepository.save(estado9);
        Municipio municipio9 = new Municipio(4106902L, estado9, "Curitiba");
        this.municipioRepository.save(municipio9);


        Regiao regiao10 = new Regiao("NE");
        this.regiaoRepository.save(regiao10);
        Estado estado10 = new Estado("PE", regiao10);
        this.estadoRepository.save(estado10);
        Municipio municipio10 = new Municipio(2611606L, estado10, "Recife");
        this.municipioRepository.save(municipio10);


        Regiao regiao11 = new Regiao("S");
        this.regiaoRepository.save(regiao11);
        Estado estado11 = new Estado("RS", regiao11);
        this.estadoRepository.save(estado11);
        Municipio municipio11 = new Municipio(4305108L, estado11, "Caxias do Sul");
        this.municipioRepository.save(municipio11);


        Regiao regiao12 = new Regiao("S");
        this.regiaoRepository.save(regiao12);
        Estado estado12 = new Estado("RS", regiao12);
        this.estadoRepository.save(estado12);
        Municipio municipio12 = new Municipio(4302105L, estado12, "Bento Gonçalves");
        this.municipioRepository.save(municipio12);


        Regiao regiao13 = new Regiao("S");
        this.regiaoRepository.save(regiao13);
        Estado estado13 = new Estado("RS", regiao13);
        this.estadoRepository.save(estado13);
        Municipio municipio13 = new Municipio(4318705L, estado13, "São Leopoldo");
        this.municipioRepository.save(municipio13);


        Regiao regiao14 = new Regiao("SE");
        this.regiaoRepository.save(regiao14);
        Estado estado14 = new Estado("RJ", regiao14);
        this.estadoRepository.save(estado14);
        Municipio municipio14 = new Municipio(3303906L, estado14, "Petrópolis");
        this.municipioRepository.save(municipio14);


        Regiao regiao15 = new Regiao("SE");
        this.regiaoRepository.save(regiao15);
        Estado estado15 = new Estado("MG", regiao15);
        this.estadoRepository.save(estado15);
        Municipio municipio15 = new Municipio(3170206L, estado15, "Uberlândia");
        this.municipioRepository.save(municipio15);


        Regiao regiao16 = new Regiao("S");
        this.regiaoRepository.save(regiao16);
        Estado estado16 = new Estado("RS", regiao16);
        this.estadoRepository.save(estado16);
        Municipio municipio16 = new Municipio(4314407L, estado16, "Pelotas");
        this.municipioRepository.save(municipio16);


        Regiao regiao17 = new Regiao("SE");
        this.regiaoRepository.save(regiao17);
        Estado estado17 = new Estado("SP", regiao17);
        this.estadoRepository.save(estado17);
        Municipio municipio17 = new Municipio(3509502L, estado17, "Campinas");
        this.municipioRepository.save(municipio17);


        Regiao regiao18 = new Regiao("S");
        this.regiaoRepository.save(regiao18);
        Estado estado18 = new Estado("RS", regiao18);
        this.estadoRepository.save(estado18);
        Municipio municipio18 = new Municipio(4314100L, estado18, "Passo Fundo");
        this.municipioRepository.save(municipio18);


        Regiao regiao19 = new Regiao("S");
        this.regiaoRepository.save(regiao19);
        Estado estado19 = new Estado("RS", regiao19);
        this.estadoRepository.save(estado19);
        Municipio municipio19 = new Municipio(4314902L, estado19, "Porto Alegre");
        this.municipioRepository.save(municipio19);
    }

}