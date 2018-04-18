package com.viridis.recruter.api.controller;

import java.sql.SQLException;
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

import com.viridis.recruter.api.entity.Equipamento;
import com.viridis.recruter.api.repository.EquipamentoRepository;

/**
 * Controller da entidade de equipamento
 * 
 * @author mauro.chaves
 *
 */
@RestController
@RequestMapping("api/equipamentos")
public class EquipamentoController {

	private final EquipamentoRepository equipamentoRepository;

	public EquipamentoController(EquipamentoRepository equipamentoRepository) {
		this.equipamentoRepository = equipamentoRepository;
	}

	/**
	 * Retorna todos os equipamentos cadastrados na database
	 * 
	 * @return lista de Equipamentos
	 */
	@GetMapping
	public Iterable<Equipamento> getAll() {
		return this.equipamentoRepository.findAll();
	}

	/**
	 * Método que retorna um equipamento pssando com parametro o id
	 * 
	 * @param equipamentoId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity findOne(@PathVariable Long id) {
		Optional<Equipamento> byId = this.equipamentoRepository.findById(id);
		return orElseReturn(byId, id);
	}

	/**
	 * Método que salvar um equipamento e por boas praticas retorna o status 200 qnd
	 * sucesso e 401 qnd erro.
	 * 
	 * @param equipamento
	 * @throws SQLException
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid Equipamento equipamento) {
		this.equipamentoRepository.save(equipamento);
	}

	/**
	 * Método que altera um equipamento do database
	 * 
	 * @param equipamentoId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PutMapping(value = "/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody Equipamento equipamento) {

		Equipamento eq = this.equipamentoRepository.findOne(id);

		if (eq == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipamento is not found");
		} else {
			eq.setCodigo(equipamento.getCodigo());
			eq.setDescricao(equipamento.getDescricao());
			eq.setFabricante(equipamento.getFabricante());
			eq.setStatusEquipamento(equipamento.getStatusEquipamento());
			eq.setTipoEquipamento(equipamento.getTipoEquipamento());
			equipamentoRepository.save(eq);
			return ResponseEntity.ok().build();
		}
	}

	/**
	 * Método que exclui um equipamento da database
	 * 
	 * @param fabricanteId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		Optional<Equipamento> byId = this.equipamentoRepository.findById(id);
		if (byId == null) {
			return ResponseEntity.notFound().build();
		} else if (byId.isPresent()) {
			this.equipamentoRepository.delete(byId.get());
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Register is not found");
		} else if (optional.isPresent()) {
			return optional.map(ResponseEntity::ok).get();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Register is not found");
		}
	}

}
