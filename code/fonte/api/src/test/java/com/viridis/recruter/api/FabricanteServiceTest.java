package com.viridis.recruter.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.viridis.recruter.api.controller.FabricanteController;
import com.viridis.recruter.api.repository.FabricanteRepository;
import com.viridis.recruter.api.service.FabricanteServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FabricanteServiceTest {

	@Autowired
	private FabricanteController controller;

	protected MockMvc mockMvc;
	public FabricanteServiceImpl fabricanteService;
	@Mock
	public FabricanteRepository fabricanteRepository;
	
	protected MockHttpServletRequestBuilder find;
	
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		fabricanteService = new FabricanteServiceImpl(fabricanteRepository);
		this.find = get("api/fabricantes/buscarFabricante");
		this.controller.getTodosFabricantes();
	}


	@Test
	public void contexLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
}
