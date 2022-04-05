package api.theVelopers.sas.service.impl;

import org.springframework.stereotype.Service;

import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.repository.EmpresaRepository;

@Service
public class TransformacaoDadosServiceImpl extends CRUDServiceImpl<Empresa, Long> {
	
	private final EmpresaRepository empresaRepository;
	
	public TransformacaoDadosServiceImpl( 
			EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}
	
	

}
