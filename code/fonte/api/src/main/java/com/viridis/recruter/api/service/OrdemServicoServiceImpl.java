package com.viridis.recruter.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viridis.recruter.api.entity.OrdemServico;
import com.viridis.recruter.api.repository.OrdemServicoRepository;

/**
 * Implementação dos métodos da interface de ordem de serviço
 * 
 * @author mauro.chaves
 *
 */
@Service
public class OrdemServicoServiceImpl implements OrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Override
	public List<OrdemServico> findAll() {
		List<OrdemServico> listaOrdemServicos = new ArrayList<>();
		try {
			listaOrdemServicos = (List<OrdemServico>) this.ordemServicoRepository.findAll();
			if (!listaOrdemServicos.isEmpty()) {
				return listaOrdemServicos;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return listaOrdemServicos;
	}

	@Override
	public OrdemServico findOne(Long idOrdemServico) {
		OrdemServico ordemServico = new OrdemServico();
		try {
			if (idOrdemServico != null) {
				ordemServico = this.ordemServicoRepository.findOne(idOrdemServico);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return ordemServico;
	}

	@Override
	public void salvarOrdemServico(OrdemServico ordemServico) {
		if(ordemServico.getId() == null) {
			ordemServico.setDataAbertura(new Date());
		}
		if (ordemServico != null) {
			this.ordemServicoRepository.save(ordemServico);
		}
	}

	@Override
	public void deletarOrdemServico(Long idOrdemServico) {
		this.ordemServicoRepository.delete(idOrdemServico);
	}
}
