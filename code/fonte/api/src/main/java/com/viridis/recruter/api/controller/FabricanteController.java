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

import com.viridis.recruter.api.entity.Fabricante;
import com.viridis.recruter.api.service.FabricanteService;

/**
 * Controller da entidade de fabricante que possui relacionamento com a entidade de equipamento
 * @author mauro.chaves
 *
 */
@RestController
@RequestMapping("api/fabricante/")
public class FabricanteController {
		
	@Autowired
	private  FabricanteService fabricanteService;
	
	/**
	 * Retorna todos os fabricantes cadastrados na database
	 * @return lista de Fabricantes
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
    public Iterable<Fabricante> getTodosFabricantes() {
        return fabricanteService.findAll();
    }
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{fabricanteId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Fabricante findById(@PathVariable(value = "fabricanteId") Long fabricanteId) {
		return fabricanteService.findById(fabricanteId);
	}
	
	@PostMapping(value = {"/novoFabricante"})
	public void salvarFabricante(@RequestBody Fabricante fabricante) throws SQLException {
		try {
			this.fabricanteService.salvarFabricante(fabricante);
        } catch (ServiceException e) {
        	//TODO: Fazer tratamento de exceção
        }
		
	}

	
	
}
