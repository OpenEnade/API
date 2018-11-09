package br.com.openenade.api.nota;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public List<Nota> getAll() {
        return this.notaRepository.findAll();
    }

}
