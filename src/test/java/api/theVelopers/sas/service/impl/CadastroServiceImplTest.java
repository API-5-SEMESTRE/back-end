package api.theVelopers.sas.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.enumeration.TipoUsuario;

@SpringBootTest
@Rollback
class CadastroServiceImplTest {
	
	@Autowired
	private UsuarioServiceImpl service;
	
	@Test
	void criarUsuarioDeveFuncionar() {
		Usuario usu = new Usuario();
		
		usu.setEmail("exemplo@gmail.com");
		usu.setNome("Exemplo do Teste");
		usu.setSenha("senhaforte");
		usu.setTipoAcesso(TipoUsuario.VENDEDOR);
		
		service.salvar(usu);
		
		assertTrue(usu.getId()!=null);
	}

}
