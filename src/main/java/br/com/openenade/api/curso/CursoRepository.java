package br.com.openenade.api.curso;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    public Optional<Curso> findOneByNome(String nome);

}
