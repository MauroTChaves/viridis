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

import com.viridis.recruter.api.entity.OrdemServico;
import com.viridis.recruter.api.service.OrdemServicoService;

/**
 * Controller da entidade de OrdemServico que possui relacionamento com a entidade
 * de equipamento
 * 
 * @author mauro.chaves
 *
 */
@RestController
@RequestMapping("api/ordensServico")
public class OrdemServicoController {

	@Autowired
	private OrdemServicoService ordemServicoService;

	/**
	 * Retorna todos os OrdemServicos cadastrados na database
	 * 
	 * @return lista de OrdemServicos
	 */
	@GetMapping()
	public Iterable<OrdemServico> getTodosOrdemServicos() {
		return ordemServicoService.findAll();
	}

	/**
	 * Busca um OrdemServico pelo id
	 * 
	 * @param OrdemServicoId
	 * @return
	 */
	@GetMapping(value = "/{ordemServicoId}")
	public OrdemServico findById(@PathVariable(value = "ordemServicoId") Long ordemServicoId) {
		return ordemServicoService.findOne(ordemServicoId);
	}

	/**
	 * Método que inseri o OrdemServico do database
	 * 
	 * @param OrdemServico
	 * @throws SQLException
	 */
	@PostMapping(value = { "/novaOrdemServico" })
	public void salvarOrdemServico(@RequestBody OrdemServico ordemServico) throws SQLException {
		try {
			this.ordemServicoService.salvarOrdemServico(ordemServico);
		} catch (ServiceException e) {
			System.err.println(e.getMessage());
		}

	}

	// TODO : Erro no método de alteração
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public OrdemServico editarOrdemServico(@RequestBody OrdemServico ordemServico) {
		OrdemServico ordemServicoAlterada = this.ordemServicoService.findOne(ordemServico.getId());
		try {
			this.ordemServicoService.salvarOrdemServico(ordemServicoAlterada);
			return ordemServicoAlterada;
		} catch (ServiceException e) {
			// TODO: Fazer tratamento de exceção
		}
		return ordemServico;

	}

	/**
	 * Método que exclui um OrdemServico da database
	 * 
	 * @param OrdemServicoId
	 * @return
	 */
	@DeleteMapping(value = "/{OrdemServicoId}")
	public String deletarOrdemServico(@PathVariable(value = "OrdemServicoId") Long ordemServicoId) {
		String statusDelecao = "";
		try {
			OrdemServico ordemServicoDeletada = this.ordemServicoService.findOne(ordemServicoId);
			if (ordemServicoDeletada != null) {
				this.ordemServicoService.deletarOrdemServico(ordemServicoDeletada.getId());
				statusDelecao = "OrdemServico " + ordemServicoDeletada.getCodigo() + " deletado com sucesso";
			} else {
				statusDelecao = "OrdemServico de id " + ordemServicoId + " não encontrado.";
			}
		} catch (Exception e) {
			statusDelecao = "Erro ao deletar OrdemServico";
		}
		return statusDelecao;
	}

}
