package com.viridis.recruter.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.viridis.recruter.api.entity.Fabricante;
import com.viridis.recruter.api.repository.FabricanteRepository;

/**
 * Controller da entidade de fabricante que possui relacionamento com a entidade
 * de equipamento
 * 
 * @author mauro.chaves
 *
 */
@RestController
@RequestMapping("api/fabricantes")
public class FabricanteController {

	private final FabricanteRepository fabricanteRepository;

	public FabricanteController(FabricanteRepository fabricanteRepository) {
		this.fabricanteRepository = fabricanteRepository;
	}

	/**
	 * Retorna todos os fabricantes cadastrados na database
	 * 
	 * @return lista de Fabricantes
	 */

	@GetMapping
	public Iterable<Fabricante> getAll() {
		return this.fabricanteRepository.findAll();
	}

	/**
	 * Busca um fabricante pelo id
	 * 
	 * @param fabricanteId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity findOne(@PathVariable Long id) {
		Optional<Fabricante> byId = this.fabricanteRepository.findById(id);
		return orElseReturn(byId, id);
	}

	/**
	 * Método que inseri o fabricante do database
	 * 
	 * @param fabricante
	 * 
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Fabricante fabricante) {
		this.fabricanteRepository.save(fabricante);
	}

	/**
	 * Método que exclui um fabricante da database
	 * 
	 * @param fabricanteId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		Optional<Fabricante> byId = this.fabricanteRepository.findById(id);
		if (byId == null) {
			return ResponseEntity.notFound().build();
		} else if (byId.isPresent()) {
			this.fabricanteRepository.delete(byId.get());
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * Método que altera um fabricante da database
	 * 
	 * @param fabricanteId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PutMapping(value = "/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody Fabricante fabricante) {

		Fabricante fb = this.fabricanteRepository.findOne(id);

		if (fb == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fabricant is not found");
		} else {
			fb.setCodigo(fabricante.getCodigo());
			fb.setNome(fabricante.getNome());
			fabricanteRepository.save(fb);
			return ResponseEntity.ok().build();
		}
	}

	/**
	 * Método auxiliar para retornar status ok ou not found
	 * 
	 * @param optional
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private <T> ResponseEntity orElseReturn(Optional<T> optional, Long id) {
		if (optional == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fabricant is not found");
		} else if (optional.isPresent()) {
			return optional.map(ResponseEntity::ok).get();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fabricant is not found");
		}
	}

}
