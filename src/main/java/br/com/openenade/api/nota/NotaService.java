package br.com.openenade.api.nota;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public Nota save(Nota nota) {
        return this.notaRepository.save(nota);
    }

    public Optional<Nota> getNotaById(NotaId id) {
        return this.notaRepository.findById(id);
    }

    public List<Nota> getAll() {
        return this.notaRepository.findAll();
    }

    public boolean deleteNotaById(NotaId id) {
        if (this.notaRepository.existsById(id)) {
            this.notaRepository.deleteById(id);
        } else {
            return false;
        }

        return true;
    }

}
