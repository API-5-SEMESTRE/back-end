package api.theVelopers.sas.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.dto.UsuarioDTO;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.exception.NegocioException;
import api.theVelopers.sas.repository.UsuarioRepository;
import api.theVelopers.sas.service.UsuarioService;

import static api.theVelopers.sas.constant.MensagemErroConstant.NENHUM_RESULTADO;

/**
 * 
 * @author jef
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public UsuarioDTO salvar(Usuario usuario) {
		Usuario novoUsuario = repo.save(usuario);

		return Usuario.paraDTO(novoUsuario);
	}

	@Override
	public UsuarioDTO procurarPorId(Long id) {
		Optional<Usuario> usuario = repo.findById(id);

		return Usuario.paraDTO(usuario.get());
	}

	@Override
	public UsuarioDTO procurarPorEmail(String email) {
		Optional<Usuario> usuario = repo.findUsuarioByEmail(email);
		
		if(usuario.isEmpty()) {
			throw new NegocioException(NENHUM_RESULTADO);
		}

		return Usuario.paraDTO(usuario.get());
	}

	@Override
	public UsuarioDTO salvarComFlush(Usuario usuario) {
		Usuario novoUsuario = repo.saveAndFlush(usuario);

		return Usuario.paraDTO(novoUsuario);
	}

	@Override
	public List<UsuarioDTO> buscarTodos() {
		List<Usuario> usuarios = repo.findAll();

		return usuarios.stream().map(Usuario::paraDTO).collect(Collectors.toList());
	}
	
	@Override
	public List<UsuarioDTO> buscarTodosVendedores() {
		List<Usuario> usuarios = repo.findAllVendedor();
		
		return usuarios.stream().map(Usuario::paraDTO).collect(Collectors.toList());
	}

	@Override
	public List<UsuarioDTO> salvarTodos(Iterable<Usuario> usuarios) {
		List<Usuario> novosUsuarios = repo.saveAll(usuarios);

		return novosUsuarios.stream().map(Usuario::paraDTO).collect(Collectors.toList());
	}

	@Override
	public List<UsuarioDTO> salvarTodosFlush(Iterable<Usuario> usuarios) {
		List<Usuario> novosUsuarios = repo.saveAllAndFlush(usuarios);

		return novosUsuarios.stream().map(Usuario::paraDTO).collect(Collectors.toList());
	}

	@Override
	public void deletar(Long id) {
		repo.deleteById(id);
	}

	@Override
	public void deletarTodos(Iterable<Usuario> usuarios) {
		repo.deleteAllInBatch(usuarios);
	}
	
	@Override
	public UsuarioDTO atualizar(Long id, Usuario usuario) {
		Usuario usuarioBd = repo.findById(id).get();
		
		usuarioBd.setEmail(usuario.getEmail());
		usuarioBd.setNome(usuario.getNome());
		usuarioBd.setTipoAcesso(usuario.getTipoAcesso());
		
		repo.saveAndFlush(usuarioBd);
		
		return Usuario.paraDTO(usuarioBd);
	}
}
