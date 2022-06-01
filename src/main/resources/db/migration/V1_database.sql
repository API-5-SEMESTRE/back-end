-- tables
-- Table: cidade
CREATE TABLE IF NOT EXISTS cidade (
    cid_id number(8)  NOT NULL,
    cid_descricao varchar2(50)  NOT NULL,
    cid_sigla_estado varchar2(2)  NOT NULL,
    cid_reg_ibge varchar2(25)  NOT NULL,
    CONSTRAINT cidade_pk PRIMARY KEY (cid_id)
) ;

-- Table: cnae
CREATE TABLE IF NOT EXISTS cnae (
    cnae_id number(8)  NOT NULL,
    cnae_cod number(8)  NOT NULL,
    cnae_desc varchar2(150)  NOT NULL,
    CONSTRAINT cnae_ak_1 UNIQUE (cnae_cod),
    CONSTRAINT cnae_pk PRIMARY KEY (cnae_id)
) ;

-- Table: usuario
CREATE TABLE IF NOT EXISTS usuario (
    usu_id number(8)  NOT NULL,
    usu_nome varchar2(20)  NOT NULL,
    usu_email varchar2(40)  NOT NULL,
    usu_senha varchar2(20)  NOT NULL,
    usu_tipoacesso varchar2(20)  NOT NULL,
    CONSTRAINT usuario_pk PRIMARY KEY (usu_id)
) ;

-- Table: empresa
CREATE TABLE IF NOT EXISTS empresa (
    emp_cnpj number(14)  NOT NULL,
    cid_id number(8)  NOT NULL,
    cnae_id number(8),
    usu_id number(8),
    emp_origem varchar2(20)  NOT NULL,
    emp_data_cadastro_vendedor timestamp,
    CONSTRAINT cid_id_fk FOREIGN KEY(cid_id) REFERENCES cidade (cid_id),
    CONSTRAINT cnae_id_fk FOREIGN KEY (cnae_id) REFERENCES cnae (cnae_id),
    CONSTRAINT usu_id_fk FOREIGN KEY (usu_id) REFERENCES cnae (usu_id),	
    CONSTRAINT empresa_pk PRIMARY KEY (emp_cnpj)
) ;

-- Table: consumo
CREATE TABLE IF NOT EXISTS consumo (
    cons_mesref timestamp  NOT NULL,
    emp_cnpj number(14)  NOT NULL,
    cons_consumo number(8)  NOT NULL,
    CONSTRAINT emp_cnpj_fk FOREIGN KEY (emp_cnpj) REFERENCES empresa (emp_cnpj),
    CONSTRAINT consumo_pk PRIMARY KEY (cons_mesref,empresa_emp_cnpj)
) ;


-- End of file.