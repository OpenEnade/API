package br.com.openenade.api.universidade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.openenade.api.exceptions.ResourceNotFound;
import br.com.openenade.api.municipio.Municipio;
import br.com.openenade.api.curso.Curso;
import br.com.openenade.api.municipio.MunicipioService;

@Service
@Transactional
public class UniversidadeService {

  @Autowired
  private UniversidadeRepository universidadeRepository;

  @Autowired
  private MunicipioService municipioService;

  public Universidade save(Universidade universidade) {
    Optional<Municipio> optMunicipio =
        this.municipioService.getOptionalByCodigo(universidade.getCampus().getCodigo());
    Municipio newCampus;
    if (optMunicipio.isPresent()) {
      newCampus = optMunicipio.get();
    } else {
      newCampus = this.municipioService.save(universidade.getCampus());
    }
    universidade.setCampus(newCampus);
    return this.universidadeRepository.save(universidade);

  }

  public Collection<Universidade> getAll() {
    return this.universidadeRepository.findAll();
  }

  // Issue #38
  public Collection<Universidade> getAllByCodigoIES(Long codigoIES) {

    return this.universidadeRepository.findAllByCodigoIES(codigoIES);

  }

  public Universidade getUniversidadeByCodigoIES(Long codigoIES) {
    Optional<Universidade> optUnivesidade = this.universidadeRepository.findByCodigoIES(codigoIES);
    if (optUnivesidade.isPresent()) {
      return optUnivesidade.get();
    } else {
      throw new ResourceNotFound("" + codigoIES);
    }
  }

  public void deleteUniversidadeByCodigoIES(Long codigoIES) {
    this.universidadeRepository.deleteByCodigoIES(codigoIES);
  }

  public void deleteUniversidadesByMunicipioCodigo(Municipio campus) {

    this.universidadeRepository.deleteAllByCampus(campus);
  }

  public Optional<Universidade> getUniversidadeById(Long codigoIES, Long codigoMunicipio) {
    UniversidadeId id = new UniversidadeId(codigoIES, codigoMunicipio);
    return this.universidadeRepository.findById(id);
  }

  public void deleteUniversidadeById(Long codigoIES, Municipio campus) {

    UniversidadeId id = new UniversidadeId(codigoIES, campus.getCodigo());
    this.universidadeRepository.deleteById(id);
  }

  /**
   * Seleciona todas as universidades que, em seu conjunto de cursos, o nome tenha a string
   * nomeCurso.
   * 
   * @param nomeCurso = O string que representa nome genérico do curso.
   * @return Uma colecao de universidades que os nomes dos cursos dão match com o parametro.
   */
  public Collection<Universidade> getAllByNomeCurso(String nomeCurso) {
    Collection<Universidade> allUniversidades = this.universidadeRepository.findAll();
    Collection<Universidade> matchedUniversidades = new ArrayList<>();

    for (Universidade universidade : allUniversidades) {
      Iterator<Curso> i = universidade.getCursos().iterator();
      while (i.hasNext()) {
        if (i.next().getNome().equalsIgnoreCase(nomeCurso))
          matchedUniversidades.add(universidade);
      }
    }
    return matchedUniversidades;
  }
}
