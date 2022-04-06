package api.theVelopers.sas.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Cidade;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.repository.CidadeRepository;
import api.theVelopers.sas.repository.EmpresaRepository;
import api.theVelopers.sas.service.TransformacaoDadosService;
import api.theVelopers.sas.utils.LeitorCSVUtils;

@Service
public class TransformacaoDadosServiceImpl extends CRUDServiceImpl<Empresa, Long> implements TransformacaoDadosService{
	
	private final EmpresaRepository empresaRepo;
	private final CidadeRepository cidadeRepo;
	
	public TransformacaoDadosServiceImpl(EmpresaRepository empresaRepo,
											CidadeRepository cidadeRepo) {
		this.empresaRepo = empresaRepo;
		this.cidadeRepo = cidadeRepo;
	}
	
	@Override
	public List<String[]> carregarTransformarDados(MultipartFile arquivo) {
		List<String[]> linhas = LeitorCSVUtils.carregarDados(arquivo);
		return linhas;
	}
	
	private List<Cidade> transformarDados(Map<String, List<String>> map) {
		Set<Cidade> cidades = new HashSet<>();
		
		for() {
			for(int j=0;j<map.get(cidades))
		}
	}
	
	private Cidade construirCidade(String id, String desc, String siglaEstado, String ibge) {
		
		Cidade cidade = new Cidade();
		
		cidade.setId(Long.valueOf(id));
		cidade.setDescricao(desc);
		cidade.setSiglaEstado(siglaEstado);
		cidade.setRegistroIbge(ibge);
		
		return cidade;
	}
	
	

}
