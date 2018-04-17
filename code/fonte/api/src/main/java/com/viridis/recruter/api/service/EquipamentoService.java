package com.viridis.recruter.api.service;

import java.util.List;

import com.viridis.recruter.api.entity.Equipamento;

public interface EquipamentoService {

	/**
	 * Retorna a lista de todos os fabricante cadastrados no database
	 */
	List<Equipamento> findAll();

	/**
	 * 
	 * @param id
	 * @return Equipamento passando como parametro o id.
	 */
	Equipamento findOne(Long id);

	/**
	 * Método para salvar um novo Equipamento no database
	 * 
	 * @param Equipamento
	 */
	void salvarEquipamento(Equipamento equipamento);

	/**
	 * Método para deletar um Equipamento no database
	 * 
	 * @param idEquipamento
	 */
	void deletarEquipamento(Long idEquipamento);
}
