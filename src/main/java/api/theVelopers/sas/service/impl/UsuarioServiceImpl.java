package api.theVelopers.sas.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.dto.UsuarioDTO;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.repository.UsuarioRepository;
import api.theVelopers.sas.service.UsuarioService;

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
}
