package com.viridis.recruter.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.viridis.recruter.api.entity.Equipamento;

/**
 * 
 * @author mauro.chaves
 *
 */
public interface EquipamentoRepository extends CrudRepository<Equipamento, Long> {
	/**
	 * Método que retona um equipamento cadastrado
	 * @param id
	 * @return
	 */
	Equipamento findById(Long id);
}
