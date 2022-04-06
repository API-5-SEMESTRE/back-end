package api.theVelopers.sas.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public Map<String, List<String> > carregarDadosEmpresa(MultipartFile arquivo) {
		
		Map<String, List<String> > map = LeitorCSVUtils.carregarDados(arquivo);
		return map; 
	}
	
	public List<Empresa> transformarDados(Map<String, List<String>> map) {
		//TODO terminar
		return null;
	}
	
	private Empresa construirEmpresa(String campoCNPJ, String campoCidade, 
											String campoCNAE, String origem) {
		//TODO terminar
		return null;
	}
}
