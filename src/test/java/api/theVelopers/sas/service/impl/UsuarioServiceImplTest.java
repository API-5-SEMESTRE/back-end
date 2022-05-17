package api.theVelopers.sas.service.impl;

import static api.theVelopers.sas.constant.MensagemErroConstant.NENHUM_RESULTADO;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import api.theVelopers.sas.entity.CarteiraVendedor;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.enumeration.TipoUsuario;
import api.theVelopers.sas.exception.NegocioException;
import api.theVelopers.sas.service.CarteiraVendedorService;
import api.theVelopers.sas.service.UsuarioService;

@SpringBootTest
@Transactional
class UsuarioServiceImplTest {
	
	@Autowired
	private UsuarioService service;
	@Autowired
	private CarteiraVendedorService cartServ;
	
	@Test
	@Rollback
	void criarUsuarioDeveFuncionar() {
		Usuario usu = new Usuario();
		
		usu.setEmail("exemplo@gmail.com");
		usu.setNome("Exemplo do Teste");
		usu.setSenha("senhaforte");
		usu.setTipoAcesso(TipoUsuario.VENDEDOR);
		
		service.salvar(usu);
		
		assertTrue(usu.getId()!=null);
	}
	
	@Test
	@Rollback
	void criarCarteiraVendedorDeveFuncionar() {
		CarteiraVendedor carteira = cartServ.criarCarteiraVendedor(69l);
		assertTrue(carteira != null);
	}
	
	@Test
	@Rollback
	void rankingMelhoresVendedoresDeveFuncionar() {
		Map<String, Long> ranking = cartServ.procurarMelhoresVendedores();
		List<String> vendedores = new ArrayList<>(ranking.keySet());
		assertTrue(StringUtils.equals(vendedores.get(0), "(71) Devanir"));
	}
	
	@Test
	@Rollback
	void procurarDeveRetornarErro() {
		
		Throwable erro = assertThrows(NegocioException.class, () -> service.procurarPorEmail("email.com.br"));
		
		assertTrue(StringUtils.equals(erro.getMessage(), NENHUM_RESULTADO));
	}

}
