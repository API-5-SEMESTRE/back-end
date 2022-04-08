package api.theVelopers.sas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.repository.EmpresaRepository;
import api.theVelopers.sas.service.EmpresaService;

@Service
public class EmpresaServiceImpl extends CrudServiceImpl<Empresa, Long> implements EmpresaService{
	
	@Autowired
	EmpresaRepository repo;
	
	@Override
	public List<Empresa> findByUsuario(Usuario vendedor) {
		
		List<Empresa> empresas = repo.findByUsuario(vendedor);
		
		return empresas;
	}

}
