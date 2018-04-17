package com.viridis.recruter.api.controller;

import java.sql.SQLException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.viridis.recruter.api.entity.Fabricante;
import com.viridis.recruter.api.service.FabricanteService;

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

	@Autowired
	private FabricanteService fabricanteService;

	/**
	 * Retorna todos os fabricantes cadastrados na database
	 * 
	 * @return lista de Fabricantes
	 */
	@GetMapping()
	public Iterable<Fabricante> getTodosFabricantes() {
		return fabricanteService.findAll();
	}

	/**
	 * Busca um fabricante pelo id
	 * 
	 * @param fabricanteId
	 * @return
	 */
	@GetMapping(value = "/{fabricanteId}")
	public Fabricante findById(@PathVariable(value = "fabricanteId") Long fabricanteId) {
		return fabricanteService.findOne(fabricanteId);
	}

	/**
	 * Método que inseri o fabricante do database
	 * 
	 * @param fabricante
	 * @throws SQLException
	 */
	@PostMapping(value = { "/novoFabricante" })
	public void salvarFabricante(@RequestBody Fabricante fabricante) throws SQLException {
		try {
			this.fabricanteService.salvarFabricante(fabricante);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}

	}

	// TODO : Erro no método de alteração
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Fabricante editarFabricante(@RequestBody Fabricante fabricante) {
		Fabricante fabricanteAlterado = this.fabricanteService.findOne(fabricante.getId());
		try {
			this.fabricanteService.salvarFabricante(fabricanteAlterado);
			return fabricanteAlterado;
		} catch (ServiceException e) {
			// TODO: Fazer tratamento de exceção
		}
		return fabricante;

	}

	/**
	 * Método que exclui um fabricante da database
	 * 
	 * @param fabricanteId
	 * @return
	 */
	@DeleteMapping(value = "/{fabricanteId}")
	public String deletarFabricante(@PathVariable(value = "fabricanteId") Long fabricanteId) {
		String statusDelacao = "";
		try {
			Fabricante fabricanteDeletado = this.fabricanteService.findOne(fabricanteId);
			if (fabricanteDeletado != null) {
				this.fabricanteService.deletarFabricante(fabricanteDeletado.getId());
				statusDelacao = "Fabricante " + fabricanteDeletado.getNome() + " deletado com sucesso";
			} else {
				statusDelacao = "Fabricante de id " + fabricanteId + " não encontrado.";
			}
		} catch (Exception e) {
			statusDelacao = "Erro ao deletar fabricante";
		}
		return statusDelacao;
	}
}
