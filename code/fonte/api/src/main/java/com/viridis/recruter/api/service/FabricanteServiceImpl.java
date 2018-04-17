package com.viridis.recruter.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viridis.recruter.api.entity.Fabricante;
import com.viridis.recruter.api.repository.FabricanteRepository;

/**
 * Implenetação dos métodos da interface de serviço
 * 
 * @author mauro.chaves
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
			if (!listaFabricantes.isEmpty()) {
				return listaFabricantes;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return listaFabricantes;
	}

	@Override
	public Fabricante findOne(Long idFabricante) {
		Fabricante fabricante = new Fabricante();
		try {
			if (idFabricante != null) {
				fabricante = this.fabricanteRepository.findOne(idFabricante);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return fabricante;
	}

	@Override
	public void salvarFabricante(Fabricante fabricante) {
		if (fabricante != null) {
			this.fabricanteRepository.save(fabricante);
		}
	}

	@Override
	public void deletarFabricante(Long idFabricante) {
		this.fabricanteRepository.delete(idFabricante);
	}
}
