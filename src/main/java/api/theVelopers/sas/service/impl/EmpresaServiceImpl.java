package api.theVelopers.sas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.repository.EmpresaRepository;
import api.theVelopers.sas.service.EmpresaService;
import api.theVelopers.sas.utils.LeitorCSVUtils;

@Service
public class EmpresaServiceImpl extends CRUDServiceImpl<Empresa, Long> implements EmpresaService{
	
	private final EmpresaRepository empresaRepo;
	
	public EmpresaServiceImpl(EmpresaRepository empresaRepo) {
		this.empresaRepo = empresaRepo;
	}
	
	@Override
	public Map<String, List<String> > carregarDadosEmpresa(String nomeArquivo) {
		
		Map<String, List<String> > map = LeitorCSVUtils.carregarDados(nomeArquivo);
		return map; 
	}

}
