package com.viridis.recruter.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.viridis.recruter.api.entity.Equipamento;

/**
 * 
 * @author mauro.chaves
 *
 */
public interface EquipamentoRepository extends CrudRepository<Equipamento, Long> {
	Optional<Equipamento> findById(Long id);
}
