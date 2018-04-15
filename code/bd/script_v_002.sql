--Criando a tabela de fabricante no database viridis
-- Table: public.fabricante
-- DROP TABLE public.fabricante
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