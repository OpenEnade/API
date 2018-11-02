package br.com.openenade.stubData;

import br.com.openenade.api.regiao.Regiao;
import org.springframework.stereotype.Component;
import br.com.openenade.api.regiao.RegiaoRepository;
import org.springframework.context.event.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;

@Component
public class StubDataCreator {

    @Autowired
    private RegiaoRepository regiaoRepository;
    
    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        this.addLines();
    }
    
    public void addLines() {        
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        
        Regiao regiao0 = new Regiao("MT");
        this.regiaoRepository.save(regiao0);

        Regiao regiao1 = new Regiao("DF");
        this.regiaoRepository.save(regiao1);

        Regiao regiao2 = new Regiao("DF");
        this.regiaoRepository.save(regiao2);

        Regiao regiao3 = new Regiao("SE");
        this.regiaoRepository.save(regiao3);

        Regiao regiao4 = new Regiao("AM");
        this.regiaoRepository.save(regiao4);

        Regiao regiao5 = new Regiao("PI");
        this.regiaoRepository.save(regiao5);

        Regiao regiao6 = new Regiao("MG");
        this.regiaoRepository.save(regiao6);

        Regiao regiao7 = new Regiao("MG");
        this.regiaoRepository.save(regiao7);

        Regiao regiao8 = new Regiao("PR");
        this.regiaoRepository.save(regiao8);

        Regiao regiao9 = new Regiao("PR");
        this.regiaoRepository.save(regiao9);

        Regiao regiao10 = new Regiao("PE");
        this.regiaoRepository.save(regiao10);

        Regiao regiao11 = new Regiao("RS");
        this.regiaoRepository.save(regiao11);

        Regiao regiao12 = new Regiao("RS");
        this.regiaoRepository.save(regiao12);

        Regiao regiao13 = new Regiao("RS");
        this.regiaoRepository.save(regiao13);

        Regiao regiao14 = new Regiao("RJ");
        this.regiaoRepository.save(regiao14);

        Regiao regiao15 = new Regiao("MG");
        this.regiaoRepository.save(regiao15);

        Regiao regiao16 = new Regiao("RS");
        this.regiaoRepository.save(regiao16);

        Regiao regiao17 = new Regiao("SP");
        this.regiaoRepository.save(regiao17);

        Regiao regiao18 = new Regiao("RS");
        this.regiaoRepository.save(regiao18);

        Regiao regiao19 = new Regiao("RS");
        this.regiaoRepository.save(regiao19);
    }

}