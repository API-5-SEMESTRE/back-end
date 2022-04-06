package api.theVelopers.sas.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Cidade;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.repository.CidadeRepository;
import api.theVelopers.sas.repository.EmpresaRepository;
import api.theVelopers.sas.service.TransformarDadosService;
import api.theVelopers.sas.utils.LeitorCSVUtils;

@Service
public class TransformarDadosServiceImpl extends CRUDServiceImpl<Empresa, Long> implements TransformarDadosService{
	
	@Autowired
	private EmpresaRepository empresaRepo;
	@Autowired
	private CidadeRepository cidadeRepo;
	
	
	@Override
	public Set<Cidade> carregarTransformarDados(MultipartFile arquivo) {
		List<String[]> linhas = LeitorCSVUtils.carregarDados(arquivo);
		Map<Long, String[]> idLinha = new HashMap<>();
		Set<Cidade> cidades = new HashSet<>();
		
		linhas.stream().forEach(linha -> idLinha.put(Long.valueOf(linha[0]), linha));
		
		cidades = transformarDadosDaBase(idLinha);
		
		return cidades;
	}
	
	private Set<Cidade> transformarDadosDaBase(Map<Long, String[]> idLinha) {
		Set<Cidade> cidades = new LinkedHashSet<>();
		Set<Long> ids = new HashSet<>();
		List<Long> idsOrdenados = new ArrayList<>();
		
		ids = idLinha.keySet();
		
		idsOrdenados = ids.stream().sorted().collect(Collectors.toList());
		
		idsOrdenados.stream().forEach(id -> {
			String[] linha = idLinha.get(id);
			Cidade novaCidade = construirCidade(linha[1], linha[2], linha[3]);
			cidades.add(novaCidade);
		});
		
		cidades.forEach(c -> System.out.println(c.getDescricao()));
		
		return cidades;
	}
	
	private Cidade construirCidade(String desc, String siglaEstado, String ibge) {
		
		Cidade cidade = new Cidade();
		
		cidade.setDescricao(desc);
		cidade.setSiglaEstado(siglaEstado);
		cidade.setRegistroIbge(ibge);
		
		return cidade;
	}
	
	

}
