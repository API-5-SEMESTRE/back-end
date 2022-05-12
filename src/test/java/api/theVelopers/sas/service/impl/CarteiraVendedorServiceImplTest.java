package api.theVelopers.sas.service.impl;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import api.theVelopers.sas.entity.CarteiraVendedor;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.enumeration.SenioridadeVendedor;
import api.theVelopers.sas.enumeration.TipoUsuario;
import api.theVelopers.sas.service.CarteiraVendedorService;
import api.theVelopers.sas.service.UsuarioService;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class CarteiraVendedorServiceImplTest {
	
	@Autowired
	private CarteiraVendedorService service;
	@Autowired
	private UsuarioService usuService;

	@Test
	@Rollback
	void carteiraDeveSerSenior() {
		Usuario usu = new Usuario();
		
		usu.setEmail("exemplo@gmail.com");
		usu.setNome("Exemplo do Teste");
		usu.setSenha("senhaforte");
		usu.setTipoAcesso(TipoUsuario.VENDEDOR);
	
		LocalDateTime data = LocalDateTime.of(2010, 8, 15, 0, 0);
		usu.setDataCriacao(data);
		usuService.salvar(usu);
		
		CarteiraVendedor carteira = service.criarCarteiraVendedor(usu.getId());
		
		assertTrue(carteira.getSenioridade() == SenioridadeVendedor.SENIOR);
		
	}
	
	@Test
	@Rollback
	void carteiraDeveSerJunior() {
		Usuario usu = new Usuario();
		
		usu.setEmail("exemplo@gmail.com");
		usu.setNome("Exemplo do Teste");
		usu.setSenha("senhaforte");
		usu.setTipoAcesso(TipoUsuario.VENDEDOR);
	
		LocalDateTime data = LocalDateTime.of(2022, 8, 15, 0, 0);
		usu.setDataCriacao(data);
		usuService.salvar(usu);
		
		CarteiraVendedor carteira = service.criarCarteiraVendedor(usu.getId());
		
		assertTrue(carteira.getSenioridade() == SenioridadeVendedor.JUNIOR);
		
	}
	
	@Test
	@Rollback
	void carteiraDeveSerPleno() {
		Usuario usu = new Usuario();
		
		usu.setEmail("exemplo@gmail.com");
		usu.setNome("Exemplo do Teste");
		usu.setSenha("senhaforte");
		usu.setTipoAcesso(TipoUsuario.VENDEDOR);
	
		LocalDateTime data = LocalDateTime.of(2019, 8, 15, 0, 0);
		usu.setDataCriacao(data);
		usuService.salvar(usu);
		
		CarteiraVendedor carteira = service.criarCarteiraVendedor(usu.getId());
		
		assertTrue(carteira.getSenioridade() == SenioridadeVendedor.PLENO);
		
	}

}
