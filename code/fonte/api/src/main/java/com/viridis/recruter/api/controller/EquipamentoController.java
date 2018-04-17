package com.viridis.recruter.api.controller;

import java.sql.SQLException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.viridis.recruter.api.entity.Equipamento;
import com.viridis.recruter.api.service.EquipamentoService;

/**
 * Controller da entidade de equipamento
 * 
 * @author mauro.chaves
 *
 */
@RestController
@RequestMapping("api/equipamentos")
public class EquipamentoController {

	@Autowired
	private EquipamentoService equipamentoService;

	/**
	 * Retorna todos os equipamentos cadastrados na database
	 * 
	 * @return lista de Equipamentos
	 */
	@GetMapping()
	public Iterable<Equipamento> getTodosEquipamentos() {
		return equipamentoService.findAll();
	}

	/**
	 * Método que retorna um equipamento pssando com parametro o id
	 * 
	 * @param equipamentoId
	 * @return
	 */
	@GetMapping(value = "/{equipamentoId}")
	public Equipamento findById(@PathVariable(value = "equipamentoId") Long equipamentoId) {
		return equipamentoService.findOne(equipamentoId);
	}

	/**
	 * Método que salvar um equipamento e por boas praticas retorna o status 200 qnd
	 * sucesso e 401 qnd erro.
	 * 
	 * @param equipamento
	 * @throws SQLException
	 */
	@PostMapping(value = { "/novoEquipamento" })
	public void salvarEquipamento(@RequestBody Equipamento equipamento) throws SQLException {
		try {
			this.equipamentoService.salvarEquipamento(equipamento);
		} catch (ServiceException e) {
			System.err.println(e.toString());
		}

	}

	/**
	 * Método que faz o update de um equipamento salvo no database
	 * 
	 * @param equipamento
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Equipamento editarEquipamento(@RequestBody Equipamento equipamento) {
		Equipamento equipamentoAlterado = this.equipamentoService.findOne(equipamento.getId());
		try {
			this.equipamentoService.salvarEquipamento(equipamentoAlterado);
			return equipamentoAlterado;
		} catch (ServiceException e) {
			System.err.println(e.toString());
			return null;
		}

	}

	/**
	 * Método que exclui um equipamento do database
	 * 
	 * @param equipamentoId
	 * @return
	 */
	@DeleteMapping(value = "/{equipamentoId}")
	public String deletarEquipamento(@PathVariable(value = "equipamentoId") Long equipamentoId) {
		String statusDelacao = "";
		try {
			Equipamento equipamentoDeletado = this.equipamentoService.findOne(equipamentoId);
			if (equipamentoDeletado != null) {
				this.equipamentoService.deletarEquipamento(equipamentoDeletado.getId());
				statusDelacao = "Equipamento " + equipamentoDeletado.getDescricao() + " deletado com sucesso";
			} else {
				statusDelacao = "Equipamento de id " + equipamentoId + " não encontrado.";
			}
		} catch (Exception e) {
			statusDelacao = "Erro ao deletar Equipamento" + e.toString();
		}
		return statusDelacao;
	}

}
