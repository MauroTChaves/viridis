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

import com.viridis.recruter.api.entity.OrdemServico;
import com.viridis.recruter.api.repository.OrdemServicoRepository;

/**
 * Controller da entidade de OrdemServico que possui relacionamento com a
 * entidade de equipamento
 * 
 * @author mauro.chaves
 *
 */
@RestController
@RequestMapping("api/ordensServico")
public class OrdemServicoController {

	private final OrdemServicoRepository ordemServicoRepository;

	public OrdemServicoController(OrdemServicoRepository ordemServicoRepository) {
		this.ordemServicoRepository = ordemServicoRepository;
	}

	/**
	 * Retorna todos os OrdemServicos cadastrados na database
	 * 
	 * @return lista de OrdemServicos
	 */
	@GetMapping
	public Iterable<OrdemServico> getAll() {
		return this.ordemServicoRepository.findAll();
	}

	/**
	 * Busca um OrdemServico pelo id
	 * 
	 * @param OrdemServicoId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/{id}")
	public ResponseEntity findOne(@PathVariable Long id) {
		Optional<OrdemServico> byId = this.ordemServicoRepository.findById(id);
		return orElseReturn(byId, id);
	}

	/**
	 * Método que inseri o OrdemServico do database
	 * 
	 * @param OrdemServico
	 * @throws SQLException
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@RequestBody @Valid OrdemServico ordemServico) {
		this.ordemServicoRepository.save(ordemServico);
	}

	// TODO : Erro no método de alteração
	@SuppressWarnings("rawtypes")
	@PutMapping(value = "/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody OrdemServico ordemServico) {

		OrdemServico os = this.ordemServicoRepository.findOne(id);

		if (os == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OrdemService is not found");
		} else {
			os.setCodigo(ordemServico.getCodigo());
			os.setDataAbertura(ordemServico.getDataAbertura());
			os.setEquipamento(ordemServico.getEquipamento());
			os.setObservacao(ordemServico.getObservacao());
			os.setTipoServico(ordemServico.getTipoServico());
			os.setSituacaoOrdemServico(ordemServico.getSituacaoOrdemServico());
			ordemServicoRepository.save(os);
			return ResponseEntity.ok().build();
		}
	}

	/**
	 * Método que exclui um OrdemServico da database
	 * 
	 * @param OrdemServicoId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		Optional<OrdemServico> byId = this.ordemServicoRepository.findById(id);
		if (byId == null) {
			return ResponseEntity.notFound().build();
		} else if (byId.isPresent()) {
			this.ordemServicoRepository.delete(byId.get());
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OrdemService is not found");
		} else if (optional.isPresent()) {
			return optional.map(ResponseEntity::ok).get();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OrdemService is not found");
		}
	}

}
