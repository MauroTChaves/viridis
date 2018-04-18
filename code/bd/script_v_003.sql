--Criar uma database 
--Alterar no arquivo de application.properties o nome da database, do user e password do banco de dados.
--Rodar os scripts de criação das tabelas
--Popular as tabelas com alguns registros
-- Script 1 

-- Table: public.fabricante

-- DROP TABLE public.fabricante;

CREATE TABLE public.fabricante
(
    id_fabricante integer NOT NULL,
    codigo_fabricante character varying(10) COLLATE pg_catalog."default",
    nome_fabricante character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT pk_fabricante PRIMARY KEY (id_fabricante)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.fabricante
    OWNER to postgres;
	
INSERT INTO public.fabricante(
	id_fabricante, codigo_fabricante, nome_fabricante)
	VALUES (1, 'RXSXS', 'SSISISOSOSO');



-- Table: public.equipamento

-- DROP TABLE public.equipamento;

CREATE TABLE public.equipamento
(
    id_equipamento bigint NOT NULL,
    codigo_equipamento character varying(10) COLLATE pg_catalog."default" NOT NULL,
    descricao_equipamento character varying(50) COLLATE pg_catalog."default" NOT NULL,
    status_equipamento character varying(255) COLLATE pg_catalog."default" NOT NULL,
    tipo_equipamento character varying(255) COLLATE pg_catalog."default" NOT NULL,
    id_fabricante bigint,
    CONSTRAINT equipamento_pkey PRIMARY KEY (id_equipamento),
    CONSTRAINT fkkiis1nlqivo1vtgvl4hif7ebb FOREIGN KEY (id_fabricante)
        REFERENCES public.fabricante (id_fabricante) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.equipamento
    OWNER to postgres;
	
	
	--RODAR OS SCRIPTS DE INSERT DE EQUIPAMENTOS
	INSERT INTO public.equipamento(
	id_equipamento, codigo_equipamento, descricao_equipamento, status_equipamento, tipo_equipamento, id_fabricante)
	VALUES (?, ?, ?, ?, ?, ?);
	:
	--Valores possiveis para status_equipamento : EM_OPERACAO, EM_REVISAO, EM_MANUTENCAO, ATIVADO, DESATIVADO.
	--Valores possíveis para tipo_equiamento : BAIXA_TENSAO, MEDIA_TENSAO, ALTA_TENSAO, PROTECAO
	
	
-- Table: public.ordem_servico

-- DROP TABLE public.ordem_servico;

CREATE TABLE public.ordem_servico
(
    id_ordem_servico bigint NOT NULL,
    codigo_ordem_servico character varying(10) COLLATE pg_catalog."default" NOT NULL,
    data_abertura timestamp without time zone,
    observacao_ordem_servico character varying(255) COLLATE pg_catalog."default",
    situacao_ordem_servico character varying(255) COLLATE pg_catalog."default" NOT NULL,
    tipo_servico character varying(255) COLLATE pg_catalog."default" NOT NULL,
    id_equipamento bigint NOT NULL,
    CONSTRAINT ordem_servico_pkey PRIMARY KEY (id_ordem_servico),
    CONSTRAINT fkfp2nmawx74kcn99ppodjt6ca9 FOREIGN KEY (id_equipamento)
        REFERENCES public.equipamento (id_equipamento) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.ordem_servico
    OWNER to postgres;
	
	INSERT INTO public.ordem_servico(
	id_ordem_servico, codigo_ordem_servico, data_abertura, observacao_ordem_servico, situacao_ordem_servico, tipo_servico, id_equipamento)
	VALUES (?, ?, ?, ?, ?, ?, ?);
	
---Valores possíveis para tipo_servico :PREVENTIVO,CORRETIVO;
---Valores possíveis para situacao_ordem_servico : ABERTA,EXECUCAO,PENDENTE,FECHADA;