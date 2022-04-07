package api.theVelopers.sas.service;

import java.util.List;

import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.entity.Usuario;

public interface EmpresaService extends CRUDService<Empresa, Long>{
	
	List<Empresa> findByUsuario(Usuario vendedor);
}
