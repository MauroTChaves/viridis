package com.viridis.recruter.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viridis.recruter.api.entity.Fabricante;
import com.viridis.recruter.api.repository.FabricanteRepository;

/**
 * Implenetação dos métodos da interface de serviço
 * @author benicio
 *
 */
@Service
public class FabricanteServiceImpl implements FabricanteService {

	@Autowired
	private FabricanteRepository fabricanteRepository;
    
	@Override
	public List<Fabricante> findAll() {
		List<Fabricante> listaFabricantes = new ArrayList<>();
		try {
			listaFabricantes = (List<Fabricante>) this.fabricanteRepository.findAll();
			if(!listaFabricantes.isEmpty()) {
				return listaFabricantes;
			}else {
				return null;
			}
		} catch (Exception e) {
			// TODO: Fazer o tratamendo a exceção
		}
		return listaFabricantes;
	}


	@Override
	public Fabricante findById(Long idFabricante) {
		Fabricante fabricante = new Fabricante();
		try {
			if(idFabricante != null) {
				fabricante = this.fabricanteRepository.findById(idFabricante);
			}
			
		} catch (Exception e) {
			// TODO: Fazer o tratamendo a exceção
		}
		return fabricante;
	}
	
	@Override
	public void salvarFabricante(Fabricante fabricante) {
		if(fabricante != null) {
			this.fabricanteRepository.save(fabricante);	
		}	
	}
	
	@Override
	public void deletarFabricante(Long idFabricante) {
		this.fabricanteRepository.delete(idFabricante);
	}
}
