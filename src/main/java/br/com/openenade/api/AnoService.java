package br.com.openenade.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.openenade.api.Ano;
import br.com.openenade.api.AnoRepository;

@Service
public class AnoService {

	@Autowired
	private AnoRepository repository;

	public Collection<Ano> getAllAnos() {

		return this.repository.findAll();
	}

	public void addAno(Ano ano) {
		
		this.repository.save(ano);
	}
	
	public void updateAno(Ano ano) {
		
		this.repository.save(ano);
	}
	
	public void deleteAno(Ano ano) {
		
		this.repository.delete(ano);
	}

}
