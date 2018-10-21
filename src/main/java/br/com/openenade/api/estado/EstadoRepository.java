package br.com.openenade.api.estado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, String> {
    
    public void deleteBySiglaEstado(String siglaEstado);
}
