package br.com.openenade.api.universidade;

import java.util.Collection;

/**
 * Representa um objeto de transferencia de dados que define a estrutura de uma resposta de um
 * conjunto de universidades.
 */
public class UniversidadesResponseModel {

  private boolean success;
  private Collection<Universidade> universidades;

  public UniversidadesResponseModel(boolean success) {
    this.success = success;
  }

  public UniversidadesResponseModel(boolean success, Collection<Universidade> universidades) {
    this(success);
    this.universidades = universidades;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public Collection<Universidade> getUniversidades() {
    return universidades;
  }

  public void setUniversidades(Collection<Universidade> universidades) {
    this.universidades = universidades;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (success ? 1231 : 1237);
    result = prime * result + ((universidades == null) ? 0 : universidades.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UniversidadesResponseModel other = (UniversidadesResponseModel) obj;
    if (success != other.success)
      return false;
    if (universidades == null) {
      if (other.universidades != null)
        return false;
    } else if (!universidades.equals(other.universidades))
      return false;
    return true;
  }
}
