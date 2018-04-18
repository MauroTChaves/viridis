# viridis
Este é  projeto de uma API  RestFull usando SpringBoot, spring - data - jpa, banco de dados postgres, spring test , Mockito, etc.
Baixar o projeto do repositorio, importar no eclipse ou na sua ide de preferência e fazer o maven update. via terminal ou via ide.

Passo 1: Rodar os scripts de ciração do banco na sua base de dados ou após a aplicação ser importada e baixada as dependencias,subir o tom cat embarcado.
Passo 2: Alterar o arquivo de application propertis para a instancia da sua base de dados, configurando nome da database, user and password.

Passo 3: Incluir registros nas tabelas atraves do script v_003 disponível no repositorio.

Passo 4: Rodar a API e usar ferramentas de consumer e produces de chamadas HTTP. Recomendo o POSTMAN da Google.
Passo 5: Usar as URL disponíveis para consumir a API.
URLs disponíveis.
http://localhost:8080/api/fabricantes  Obs: quando for uma chamada GET esta URL devolver a lista de todos os fabricantes cadastrados.
http://localhost:8080/api/fabricantes/1  Obs: quando for uma chamada GET esta URL um objeto fabricante cadastrado de acordo com o id.
http://localhost:8080/api/fabricantes  Obs: quando for uma chamada POST esta URL recebe um objeto fabricante no formato json.
http://localhost:8080/api/fabricantes/1  Obs: quando for uma chamada DELETE esta URL aciona o método delete da API.


Obs: Esta API foi criada com o uso das boas práticas de API RESTFULL  e usufruindo das técnicas de progração TDD. Por isso temos nest API testes unitarios para a classe Controladora de Fabricantes FabricanteControllerTest.java a qual uso  JUNIT para rodar os testes.
Tem também a classe FabricanteControllerIntegracaoTest.java que é a implementação d teste de integração da API.
Os testes não cobrem todas as classes mas cobre  suficiente para servir de exemplo.

Usado Java 1.8
 <properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>
  
  <parent> 
         <groupId>org.springframework.boot</groupId> 
         <artifactId>spring-boot-starter-parent</artifactId> 
             <version>1.5.3.RELEASE</version>
  </parent> 


