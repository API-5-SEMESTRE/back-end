package api.theVelopers.sas.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.theVelopers.sas.dto.UsuarioDTO;
import api.theVelopers.sas.entity.CarteiraVendedor;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.service.CarteiraVendedorService;
import api.theVelopers.sas.service.UsuarioService;

@RestController
@RequestMapping(path = {"/usuario"})
public class UsuarioController {
	
	public static final String USUARIO_DELETADO = "Usuário deletado com sucesso";
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private CarteiraVendedorService carteiraService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody Usuario usuario) {
		UsuarioDTO novoUsuario = usuarioService.salvarComFlush(usuario);
		
		return new ResponseEntity<>(novoUsuario, CREATED);
	}
	
	@GetMapping("/todos-usuarios")
	public ResponseEntity<List<UsuarioDTO>> pesquisarTodosUsuarios() {
		List<UsuarioDTO> usuarios = usuarioService.buscarTodos();
		
		return new ResponseEntity<>(usuarios, OK);
	}
	
	@GetMapping("/todos-usuarios/{email}")
	public ResponseEntity<UsuarioDTO> pesquisarPorEmail(@PathVariable("email") String email) {
		UsuarioDTO usuarios = usuarioService.procurarPorEmail(email);
		
		return new ResponseEntity<>(usuarios, OK);
	}
	
	@DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable("id") Long id) {
        usuarioService.deletar(id);

        return ResponseEntity.ok(USUARIO_DELETADO);
    }
	
	@GetMapping("/carteira-vendedor/{id}")
	public ResponseEntity<CarteiraVendedor> pesquisarCarteira(@PathVariable("id") Long idUsuario) {
		CarteiraVendedor carteira = carteiraService.criarCarteiraVendedor(idUsuario);
		
		return new ResponseEntity<>(carteira, OK);
	}
}
