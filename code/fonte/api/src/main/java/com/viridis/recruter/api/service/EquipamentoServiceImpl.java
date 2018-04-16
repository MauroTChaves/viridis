package com.viridis.recruter.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viridis.recruter.api.entity.Equipamento;
import com.viridis.recruter.api.repository.EquipamentoRepository;

/**
 * Implenetação dos métodos da interface de serviço
 * @author benicio
 *
 */
@Service
public class EquipamentoServiceImpl implements EquipamentoService {

	@Autowired
	private EquipamentoRepository equipamentoRepository;
    
	@Override
	public List<Equipamento> findAll() {
		List<Equipamento> listaEquipamentos = new ArrayList<>();
		try {
			listaEquipamentos = (List<Equipamento>) this.equipamentoRepository.findAll();
			if(!listaEquipamentos.isEmpty()) {
				return listaEquipamentos;
			}else {
				return null;
			}
		} catch (Exception e) {
			// TODO: Fazer o tratamendo a exceção
		}
		return listaEquipamentos;
	}


	@Override
	public Equipamento findById(Long idEquipamento) {
		Equipamento equipamento = new Equipamento();
		try {
			if(idEquipamento != null) {
				equipamento = this.equipamentoRepository.findById(idEquipamento);
			}
			
		} catch (Exception e) {
			// TODO: Fazer o tratamendo a exceção
		}
		return equipamento;
	}
	
	@Override
	public void salvarEquipamento(Equipamento equipamento) {
		if(equipamento != null) {
			this.equipamentoRepository.save(equipamento);	
		}	
	}
	
	@Override
	public void deletarEquipamento(Long idEquipamento) {
		this.equipamentoRepository.delete(idEquipamento);
	}
}
