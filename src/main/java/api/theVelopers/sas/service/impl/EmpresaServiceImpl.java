package api.theVelopers.sas.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.dto.EmpresaDTO;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.entity.Usuario;
import api.theVelopers.sas.repository.EmpresaRepository;
import api.theVelopers.sas.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	@Override
	public List<Empresa> findByUsuario(Usuario vendedor) {
		
		List<Empresa> empresas = empresaRepo.findByUsuario(vendedor);
		
		return empresas;
	}

	@Override
	public List<Empresa> salvarTodos(Iterable<Empresa> empresas) {
		return empresaRepo.saveAll(empresas);
	}

	@Override
	public List<Empresa> salvarTodosFlush(Iterable<Empresa> empresas) {
		return empresaRepo.saveAllAndFlush(empresas);
	}

	@Override
	public List<EmpresaDTO> procurarTodos() {
		List<Empresa> empresas = empresaRepo.findAll();
		return empresas.stream().map(Empresa::paraDTO).collect(Collectors.toList());
	}

}
