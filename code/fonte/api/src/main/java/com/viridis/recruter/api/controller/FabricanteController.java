package com.viridis.recruter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.viridis.recruter.api.entity.Fabricante;
import com.viridis.recruter.api.service.FabricanteService;

/**
 * Controller da entidade de fabricante que possui relacionamento com a entidade de equipamento
 * @author mauro.chaves
 *
 */
@RestController
@RequestMapping("api/fabricante")
public class FabricanteController {
	
	@Autowired
	private  FabricanteService fabricanteService;
	
	@RequestMapping(method = RequestMethod.GET)
    public Iterable<Fabricante> getTodosFabricantes() {
        return fabricanteService.findAll();
    }
}
