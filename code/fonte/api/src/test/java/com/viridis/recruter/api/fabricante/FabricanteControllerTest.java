package com.viridis.recruter.api.fabricante;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viridis.recruter.api.controller.FabricanteController;
import com.viridis.recruter.api.entity.Fabricante;
import com.viridis.recruter.api.repository.FabricanteRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = FabricanteController.class)
public class FabricanteControllerTest {

	private static final String API_URL = "/api/fabricantes";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private FabricanteRepository repository;

	private List<Fabricante> fabricantes = Arrays.asList(new Fabricante("29090", "HUDSON SISTEMS"),
			new Fabricante("HP", "AGAPE"));
	
	/**
	 * Teste para verificar se a lista criada acima contém 2 elementos
	 * @throws Exception
	 */
	@Test
	public void getAll() throws Exception {

		doReturn(fabricantes).when(repository).findAll();

		this.mockMvc.perform(get(API_URL)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
	}
	
	/**
	 * Teste que verifica se o 1º elemento da lista retorna acima tem nome HUDSON SISTEMS.
	 * @throws Exception
	 */
	@Test
	public void findOne() throws Exception {
		doReturn(Optional.of(fabricantes.get(0))).when(repository).findById(1L);
		this.mockMvc.perform(get(API_URL + "/1")).andExpect(status().isOk()).andExpect(jsonPath("$.nome", is("HUDSON SISTEMS")));
	}
	
	/**
	 * Teste para validar p método not found do controller
	 * @throws Exception
	 */
	@Test
    public void findOneNotFound() throws Exception {
        doReturn(Optional.of(fabricantes.get(0))).when(repository).findById(1L);
        this.mockMvc.perform(get(API_URL + "/21")).
                andExpect(status().isNotFound())
                .andExpect(content().string(is("Fabricant is not found")));
    }
		
	/**
	 * 
	 * @throws Exception
	 */
	@Test
    public void createFabricante() throws Exception {
        createPost(fabricantes.get(1))
                .andExpect(status().isCreated());

        verify(repository).save(fabricantes.get(1));
    }

    @Test
    public void deleteNotFound() throws Exception {
        doReturn(Optional.empty()).when(repository).findById(anyLong());

        this.mockMvc.perform(delete(API_URL + "/1"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void codigoBlank() throws Exception {
        Fabricante fabricante = new Fabricante();
        fabricante.setNome("TXT");
        createPost(fabricante)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void nomeBlank() throws Exception {
    	Fabricante fabricante = new Fabricante();
    	fabricante.setCodigo("XXXX");
        createPost(fabricante)
                .andExpect(status().isBadRequest());
    }
    
    /**
     * Método default pra criar fabricante
     * @param object
     * @return
     * @throws Exception
     */
    private ResultActions createPost(Object object) throws Exception {
        return this.mockMvc.perform(post(API_URL).content(mapper.writeValueAsBytes(object))
                .contentType(MediaType.APPLICATION_JSON));
    }
}
