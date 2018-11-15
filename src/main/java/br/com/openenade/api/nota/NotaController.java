package br.com.openenade.api.nota;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.openenade.api.modalidade.Modalidade;

@RestController
@CrossOrigin("*")
@RequestMapping(path = NotaController.ENDPOINT)
public class NotaController {

	public static final String ENDPOINT = "notas";

	@Autowired
	private NotaService service;

	@PostMapping
	public void postNota(@Valid @RequestBody Nota nota) {
		this.service.save(nota);
	}

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Nota>> getAll() {
		return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping(path = "/{ano}-{codigoCurso}-{modalidade}-{codigoIES}-{codigoMunicipio}")
	public ResponseEntity<Nota> getNotaByIndex(NotaIdInterface notaIdInterface) {

		Optional<Nota> optNota = null;
		try {
			optNota = this.service.getNota(notaIdInterface);
		} catch (NoSuchElementException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (optNota.isPresent()) {
			return new ResponseEntity<>(optNota.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = "/{ano}-{codigoCurso}-{modalidade}-{codigoIES}-{codigoMunicipio}")
	public ResponseEntity<String> deleteNotaByIndex(@PathVariable(name = "ano") Integer ano,
			@PathVariable(name = "codigoCurso") Long codigoCurso,
			@PathVariable(name = "modalidade") Modalidade modalidade, @PathVariable(name = "codigoIES") Long codigoIES,
			@PathVariable(name = "codigoMunicipio") Long codigoMunicipio) {

		boolean deleted = false;
		try {
			deleted = this.service.deleteNota(ano, codigoCurso, modalidade, codigoIES, codigoMunicipio);
		} catch (NoSuchElementException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if (deleted) {
			return new ResponseEntity<>("Deletado.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Nota não encontrada.", HttpStatus.NOT_FOUND);
		}
	}

}
