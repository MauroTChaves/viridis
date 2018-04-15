--Criando a instancia do banco de dados da aplicação
-- Database: viridis
-- DROP DATABASE viridis;
CREATE DATABASE viridis
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
