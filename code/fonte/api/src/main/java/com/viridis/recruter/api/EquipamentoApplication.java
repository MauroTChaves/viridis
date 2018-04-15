package com.viridis.recruter.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe de configuração do springboot para rodar o servidor de aplicação TomCat embutido na aplicação
 * @author mauro.chaves
 *
 */
@SpringBootApplication
public class EquipamentoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EquipamentoApplication.class, args);
	}

}
