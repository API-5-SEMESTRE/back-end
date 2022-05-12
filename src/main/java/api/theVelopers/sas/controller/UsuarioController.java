package api.theVelopers.sas.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api.theVelopers.sas.dto.UsuarioDTO;
import api.theVelopers.sas.entity.CarteiraVendedor;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.exception.TratamentoExcecao;
import api.theVelopers.sas.service.CarteiraVendedorService;
import api.theVelopers.sas.service.UsuarioService;


/**
 * 
 * @author jef
 *
 */
@RestController
@RequestMapping(path = {"/usuario"})
public class UsuarioController extends TratamentoExcecao{
	
	public static final String USUARIO_DELETADO = "Usuário deletado com sucesso";
	public static final String EMPRESA_ADICIONADA = "Empresa adicionada na carteira com sucesso";
	public static final String EMPRESA_REMOVIDA = "Empresa removida da carteira com sucesso";
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private CarteiraVendedorService carteiraService;
	
	@PostMapping("/cadastrar")
	@ResponseStatus(CREATED)
	public UsuarioDTO cadastrarUsuario(@RequestBody Usuario usuario) {
		return usuarioService.salvarComFlush(usuario);
	}
	
	@PutMapping("/atualizar-dados/{id}")
	@ResponseStatus(OK)
	public UsuarioDTO atualizarDados(@PathVariable("id") Long idUsuario, @RequestBody Usuario usuario) {
		return usuarioService.atualizar(idUsuario, usuario);
	}
	
	@GetMapping("/todos")
	@ResponseStatus(OK)
	public List<UsuarioDTO> pesquisarTodosUsuarios() {
		return usuarioService.buscarTodos();
	}
	
	@GetMapping("/todos-vendedores")
	@ResponseStatus(OK)
	public ResponseEntity<List<UsuarioDTO>> pesquisarTodosVendedores() {
		List<UsuarioDTO> usuarios = usuarioService.buscarTodosVendedores();
		
		return new ResponseEntity<>(usuarios, OK);
	}
	
	@GetMapping("/pesquisar-por-email/{email}")
	@ResponseStatus(OK)
	public ResponseEntity<UsuarioDTO> pesquisarPorEmail(@PathVariable("email") String email) {
		UsuarioDTO usuarios = usuarioService.procurarPorEmail(email);
		
		return new ResponseEntity<>(usuarios, OK);
	}
	
	@DeleteMapping("/deletar/{id}")
	@ResponseStatus(NO_CONTENT)
    public void deletarUsuario(@PathVariable("id") Long id) {
        usuarioService.deletar(id);
    }
	
	@GetMapping("/carteira-vendedor/{id}")
	@ResponseStatus(OK)
	public CarteiraVendedor pesquisarCarteira(@PathVariable("id") Long idUsuario) {
		return carteiraService.criarCarteiraVendedor(idUsuario);
	}
	
	@GetMapping("/ranking-vendedor/")
	@ResponseStatus(OK)
	public Map<String, Long> pesquisarRanking() {
		return carteiraService.procurarMelhoresVendedores();
	}
	
	@PutMapping("/adicionar-vendedor-empresa/{id}/{cnpj}")
	public ResponseEntity<?> adicionarVendedorEmpresa(@PathVariable("id")Long idUsuario,
														@PathVariable("cnpj")Long cnpj) {
		
		carteiraService.adicionarVendedorEmpresa(idUsuario, cnpj);
		
		return  ResponseEntity.ok(EMPRESA_ADICIONADA);
	}
	
	@DeleteMapping("/deletar-vendedor-empresa/{cnpj}")
    public ResponseEntity<?> deletarUsuarioEmpresa(@PathVariable("cnpj") Long cnpj) {
		carteiraService.removerVendedorEmpresa(cnpj);

        return ResponseEntity.ok(USUARIO_DELETADO);
    }
}
