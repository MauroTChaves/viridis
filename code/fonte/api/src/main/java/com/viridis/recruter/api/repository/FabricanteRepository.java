package com.viridis.recruter.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.viridis.recruter.api.entity.Fabricante;

/**
 * 
 * @author mauro.chaves
 *
 */
public interface FabricanteRepository extends CrudRepository<Fabricante, Long> {
	/**
	 * Método uqe retona um fabricante de equipamento
	 * @param id
	 * @return
	 */
	Fabricante findById(Long id);
}
