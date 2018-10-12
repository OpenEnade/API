package br.com.openenade.api;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.openenade.api.Ano;
import br.com.openenade.api.AnoRepository;

@Service
@Transactional
public class AnoService {

    @Autowired
    private AnoRepository repository;

    public Collection<Ano> getAllAnos() {

        return this.repository.findAll();
    }

    public ResponseEntity<String> addAno(Ano ano) {

        try {
            this.repository.save(ano);
            return ResponseEntity.status(HttpStatus.OK).body("{\"Response\": \" OK \"}");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"Response\": \" BAD REQUEST \"}");

        }
    }

    public ResponseEntity<String> updateAno(Ano ano) {

        try {
            this.repository.save(ano);
            return ResponseEntity.status(HttpStatus.OK).body("{\"Response\": \" OK \"}");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"Response\": \" BAD REQUEST \"}");

        }
    }

    public ResponseEntity<String> deleteAno(Integer ano) {

        try {
            this.repository.deleteByAno(ano);

            return ResponseEntity.status(HttpStatus.OK).body("{\"Response\": \" OK \"}");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"Response\": \"" + e.getMessage() + "\"}");

        }
    }

    public Ano getAno(Integer ano) {

        return this.repository.getElemmentByAno(ano);
        
    }

}
