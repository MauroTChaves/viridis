package com.viridis.recruter.api.fabricante;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.viridis.recruter.api.entity.Fabricante;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(value = "/init-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = "/delete-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class FabricanteControllerIntegracaoTest {
	
	private static final String API_URL = "/api/fabricantes";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EntityManager entityManager;
	
    
    /**
     * Teste verifica se tem 2 registros inseridos na tabela de fabricantes
     */
    @Test
    public void getAll() {
        ResponseEntity<Fabricante[]> response = restTemplate.getForEntity(API_URL, Fabricante[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(3);
    }
    
    
    @Test
    public void post() {
        Fabricante fabricante = new Fabricante("TTTTT", "FUSISTOR");
        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, fabricante, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        List<Fabricante> fabricantes = listFabricante();
        assertThat(fabricantes).hasSize(4);
        assertThat(fabricantes).contains(fabricante);

    }

    @SuppressWarnings("unchecked")
	private List<Fabricante> listFabricante() {
        return (List<Fabricante>) this.entityManager.createQuery("select f from Fabricante f").getResultList();
    }

}
