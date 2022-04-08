package api.theVelopers.sas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.dto.UsuarioDTO;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.repository.UsuarioRepository;
import api.theVelopers.sas.service.CadastroService;

@Service
public class CadastroServiceImpl implements CadastroService{
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	public UsuarioDTO transformarEmDTO(Usuario usuario) {
		UsuarioDTO dto = new UsuarioDTO();
		
		dto.setId(usuario.getId());
		dto.setNome(usuario.getNome());
		dto.setTipoAcesso(usuario.getTipoAcesso().toString());
		
		return dto;
	}
	
	@Override
	public List<UsuarioDTO> transformarTudoEmDTO(List<Usuario> usuarios) {
		List<UsuarioDTO> dtos = new ArrayList<>();
		usuarios.stream().forEach(u -> {
			UsuarioDTO dto = transformarEmDTO(u);
			dtos.add(dto);
		});
		return dtos;
	}
}
