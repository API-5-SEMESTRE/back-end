package api.theVelopers.sas.service;

import java.util.List;

import api.theVelopers.sas.dto.UsuarioDTO;
import api.theVelopers.sas.entity.Usuario;

public interface CadastroService extends CRUDService<Usuario, Long>{

	UsuarioDTO transformarEmDTO(Usuario usuario);

	List<UsuarioDTO> transformarTudoEmDTO(List<Usuario> usuarios);
	
}
