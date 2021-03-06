package com.viridis.recruter.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.viridis.recruter.api.entity.OrdemServico;

/**
 * 
 * @author mauro.chaves
 *
 */
public interface OrdemServicoRepository extends CrudRepository<OrdemServico, Long> {
	Optional<OrdemServico> findById(Long id);
}
