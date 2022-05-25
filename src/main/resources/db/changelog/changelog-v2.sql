-- changeset Jeferson:2:insert
insert into usuario(usu_id,usu_nome,usu_senha,usu_email,usu_tipo_acesso,usu_data_criacao) values(usuario_sequence.nextval,'Charlie',12345,'charlie.doe@email.com','VENDEDOR',CURRENT_TIMESTAMP)
