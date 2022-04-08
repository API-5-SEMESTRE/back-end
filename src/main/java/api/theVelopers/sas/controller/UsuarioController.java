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
import api.theVelopers.sas.service.CadastroService;
import api.theVelopers.sas.service.CarteiraVendedorService;

@RestController
@RequestMapping(path = {"/usuario"})
public class UsuarioController {
	
	public static final String USUARIO_DELETADO = "Usuário deletado com sucesso";
	
	@Autowired
	private CadastroService cadastroService;
	@Autowired
	private CarteiraVendedorService carteiraService;
	
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody Usuario usuario) {
		Usuario novoUsuario = cadastroService.salvarFlush(usuario);
		
		return new ResponseEntity<>(Usuario.paraDTO(novoUsuario), CREATED);
	}
	
	@GetMapping("/todos-usuarios")
	public ResponseEntity<List<UsuarioDTO>> pesquisarTodosUsuarios() {
		List<Usuario> usuarios = cadastroService.procurarTodos();
		
		return new ResponseEntity<>(usuarios.stream().map(Usuario::paraDTO).collect(Collectors.toList()), OK);
	}
	
	@DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable("id") Long id) {
        cadastroService.deletar(cadastroService.procurarPorId(id).get());

        return ResponseEntity.ok(USUARIO_DELETADO);
    }
	
	@GetMapping("/carteira-vendedor/{id}")
	public ResponseEntity<CarteiraVendedor> pesquisarCarteira(@PathVariable("id") Long idUsuario) {
		CarteiraVendedor carteira = carteiraService.criarCarteiraVendedor(idUsuario);
		
		return new ResponseEntity<>(carteira, OK);
	}
}
