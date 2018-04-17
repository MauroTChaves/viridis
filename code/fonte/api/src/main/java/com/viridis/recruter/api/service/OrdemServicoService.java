package com.viridis.recruter.api.service;

import java.util.List;

import com.viridis.recruter.api.entity.OrdemServico;

public interface OrdemServicoService {

	/**
	 * Retorna a lista de todos as ordens de serviços cadastradas no database
	 */
	List<OrdemServico> findAll();

	/**
	 * 
	 * @param id
	 * @return OrdemServico passando como parametro o id.
	 */
	OrdemServico findOne(Long id);

	/**
	 * Método para salvar um novo OrdemServico no database
	 * 
	 * @param OrdemServico
	 */
	void salvarOrdemServico(OrdemServico ordemServico);

	/**
	 * Método para deletar uma OrdemServico no database
	 * 
	 * @param idOrdemServico
	 */
	void deletarOrdemServico(Long idOrdemServico);
}
