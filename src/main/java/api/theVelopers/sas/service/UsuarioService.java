package api.theVelopers.sas.service;

import java.util.List;

import api.theVelopers.sas.dto.UsuarioDTO;
import api.theVelopers.sas.entity.Usuario;

public interface UsuarioService{
	
	UsuarioDTO salvar(Usuario usuario);
	UsuarioDTO procurarPorId(Long id);
	UsuarioDTO procurarPorEmail(String email);
	UsuarioDTO salvarComFlush(Usuario usuario);
	UsuarioDTO atualizar(Long id, Usuario usuario);
	List<UsuarioDTO> buscarTodos();
	List<UsuarioDTO> salvarTodos(Iterable<Usuario> usuarios);
	List<UsuarioDTO> salvarTodosFlush(Iterable<Usuario> usuarios);
	void deletar(Long id);
	void deletarTodos(Iterable<Usuario> usuarios);
}
