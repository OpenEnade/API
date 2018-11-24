package br.com.openenade.api.universidade;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.openenade.api.municipio.Municipio;


@Repository
public interface UniversidadeRepository extends JpaRepository<Universidade, UniversidadeId> {

  public Optional<Universidade> findByCodigoIES(Long codigoIES);

  public void deleteByCodigoIES(Long codigoIES);

  public void deleteAllByCodigoIES(Long codigo);

  public void deleteAllByCampus(Municipio campus);

  public Collection<Universidade> findAllByCodigoIES(Long codigoIES);

  // public Collection<Universidade> findAllById(UniversidadeId id);
}
