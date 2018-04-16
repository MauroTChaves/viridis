package com.viridis.recruter.api.service;

import java.util.List;

import com.viridis.recruter.api.entity.Fabricante;

public interface FabricanteService {
	
	/**
	 *Retorna a lista de todos os fabricante cadastrados no database
	 */
	List<Fabricante> findAll();
	
	/**
	 * 
	 * @param id
	 * @return Fabricante passando como parametro o id.
	 */
	Fabricante findById(Long id);
	
	/**
	 * Método para salvar um novo fabricante no database
	 * @param fabricante
	 */
	void salvarFabricante(Fabricante fabricante);
}
