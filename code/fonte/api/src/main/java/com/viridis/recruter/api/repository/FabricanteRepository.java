package com.viridis.recruter.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.viridis.recruter.api.entity.Fabricante;

/**
 * 
 * @author mauro.chaves
 *
 */
public interface FabricanteRepository extends CrudRepository<Fabricante, Long> {
	Optional<Fabricante> findById(Long id);
}
