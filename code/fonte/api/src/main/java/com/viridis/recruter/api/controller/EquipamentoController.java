package com.viridis.recruter.api.controller;

import java.sql.SQLException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.viridis.recruter.api.entity.Equipamento;
import com.viridis.recruter.api.entity.Equipamento;
import com.viridis.recruter.api.service.EquipamentoService;

/**
 * Controller da entidade de equipamento
 * @author mauro.chaves
 *
 */
@RestController
@RequestMapping("api/equipamento/")
public class EquipamentoController {
		
	@Autowired
	private  EquipamentoService equipamentoService;
	
	/**
	 * Retorna todos os equipamentos cadastrados na database
	 * @return lista de Equipamentos
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
    public Iterable<Equipamento> getTodosEquipamentos() {
        return equipamentoService.findAll();
    }
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{equipamentoId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Equipamento findById(@PathVariable(value = "equipamentoId") Long equipamentoId) {
		return equipamentoService.findById(equipamentoId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = {"/novoEquipamento"})
	public void salvarEquipamento(@RequestBody Equipamento equipamento) throws SQLException {
		try {
			this.equipamentoService.salvarEquipamento(equipamento);
        } catch (ServiceException e) {
        	//TODO: Fazer tratamento de exceção
        }
		
	}
	
	//TODO : Erro no método de alteração
	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT,
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
	public Equipamento editarEquipamento(@RequestBody Equipamento equipamento) {
		Equipamento equipamentoAlterado = this.equipamentoService.findById(equipamento.getId());
		try {
			this.equipamentoService.salvarEquipamento(equipamentoAlterado);
			return equipamentoAlterado;
        } catch (ServiceException e) {
        	//TODO: Fazer tratamento de exceção
        }	
		return equipamento;
		
	}
	
	
	@ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{equipamentoId}", method = RequestMethod.DELETE)
	public String deletarEquipamento(@PathVariable(value = "equipamentoId") Long equipamentoId) {
		String statusDelacao = "";
		try {
			Equipamento equipamentoDeletado = this.equipamentoService.findById(equipamentoId);
			if(equipamentoDeletado != null) {
				this.equipamentoService.deletarEquipamento(equipamentoDeletado.getId());
				statusDelacao = "Equipamento " + equipamentoDeletado.getDescricao() + " deletado com sucesso";
			}else {
				statusDelacao = "Equipamento de id " + equipamentoId + " não encontrado.";
			}
		} catch (Exception e) {
			statusDelacao = "Erro ao deletar Equipamento";
		}
		return  statusDelacao;
	}
	

	
}
