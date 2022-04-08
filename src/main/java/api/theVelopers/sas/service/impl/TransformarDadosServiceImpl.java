package api.theVelopers.sas.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Cidade;
import api.theVelopers.sas.entity.Cnae;
import api.theVelopers.sas.entity.Consumo;
import api.theVelopers.sas.entity.ConsumoId;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.enumeration.TipoEmpresa;
import api.theVelopers.sas.repository.CidadeRepository;
import api.theVelopers.sas.repository.CnaeRepository;
import api.theVelopers.sas.repository.EmpresaRepository;
import api.theVelopers.sas.service.TransformarDadosService;
import api.theVelopers.sas.utils.LeitorCSVUtils;

@Service
public class TransformarDadosServiceImpl implements TransformarDadosService {

	@Autowired
	private EmpresaRepository empresaRepo;
	@Autowired
	private CidadeRepository cidadeRepo;
	@Autowired
	private CnaeRepository cnaeRepo;

	@Override
	public Set<Cidade> transformarDadosCidade(MultipartFile arquivo) {
		List<String[]> linhas = LeitorCSVUtils.carregarDados(arquivo);
		Set<Cidade> cidades = new HashSet<>();

		cidades = construirCidades(linhas);

		return cidades;
	}

	private List<Long> ordenarDadosDaBaseComId(Map<Long, String[]> idLinha) {
		Set<Long> ids = new HashSet<>();
		List<Long> idsOrdenados = new ArrayList<>();

		ids = idLinha.keySet();

		idsOrdenados = ids.stream().sorted().collect(Collectors.toList());

		return idsOrdenados;
	}

	private Set<Cidade> construirCidades(List<String[]> linhas) {
		Set<Cidade> cidades = new HashSet<>();

		linhas.stream().forEach(linha -> {
			Cidade novaCidade = construirCidade(linha[0], linha[1], linha[2], linha[3]);
			cidades.add(novaCidade);
		});

		return cidades;
	}

	private Cidade construirCidade(String id, String desc, String siglaEstado, String ibge) {

		Cidade cidade = new Cidade();
		
		cidade.setId(Long.valueOf(id));
		cidade.setDescricao(desc);
		cidade.setSiglaEstado(siglaEstado);
		cidade.setRegistroIbge(ibge);

		return cidade;
	}
	
	@Override
	public Set<Cnae> transformarDadosCnae(MultipartFile arquivo) {
		List<String[]> linhas = LeitorCSVUtils.carregarDados(arquivo);
		Set<Cnae> cnaes = new HashSet<>();
		
		cnaes = construirCnaes(linhas);

		return cnaes;
	}

	private Set<Cnae> construirCnaes(List<String[]> linhas) {
		Set<Cnae> cnaes = new LinkedHashSet<>();

		linhas.stream().forEach(linha -> {
			Cnae novoCnae = construirCnae(linha[0], linha[1], linha[2]);
			cnaes.add(novoCnae);
		});

		return cnaes;
	}

	private Cnae construirCnae(String id, String codigo, String desc) {

		Cnae cnae = new Cnae();
		
		cnae.setId(Long.valueOf(id));
		cnae.setCodigo(Long.valueOf(codigo));
		cnae.setDescricao(desc);

		return cnae;
	}
	
	@Override
	public Set<Empresa> transformarDadosEmpresa(MultipartFile arquivo) {
		List<String[]> linhas = LeitorCSVUtils.carregarDados(arquivo);
		Set<Empresa> empresas = new HashSet<>();

		empresas = construirEmpresas(linhas);

		return empresas;
	}

	private Set<Empresa> construirEmpresas(List<String[]> linhas) {
		Set<Empresa> empresas = new LinkedHashSet<>();
		final HashMap<Long, Cnae> cnaesMap = construirMapCnaes();
		final HashMap<Long, Cidade> cidadesMap = construirMapCidades();
		
		for(String[] linha: linhas) {
			Empresa novaEmpresa = construirEmpresa(linha[0],linha[1],
													linha[2],linha[3],
													cidadesMap, cnaesMap);
			empresas.add(novaEmpresa);
		}

		return empresas;
	}

	private Empresa construirEmpresa(String cnpj, String idCidade, 
										String idCnae, String origem,
										HashMap<Long, Cidade> cidadesMap,
										HashMap<Long, Cnae> cnaesMap) {

		Empresa empresa = new Empresa();
		Cidade cidade = cidadesMap.get(Long.valueOf(idCidade));
		Cnae cnae = cnaesMap.get(Long.valueOf(idCnae));

		empresa.setCnpj(Long.valueOf(cnpj));
		empresa.setCidade(cidade);
		empresa.setCnae(cnae);
		Optional<TipoEmpresa> tipo = TipoEmpresa.get(origem);
		empresa.setOrigem(tipo.get());

		return empresa;
	}
	
	@Override
	public Set<Consumo> transformarDadosConsumo(MultipartFile arquivo) {
		List<String[]> linhas = LeitorCSVUtils.carregarDados(arquivo);

		return construirConsumos(linhas);
	}
	
	private Set<Consumo> construirConsumos(List<String[]> linhas) {
		Set<Consumo> consumos = new LinkedHashSet<>();
		final HashMap<Long, Empresa> empresasMap = construirMapEmpresas();
		
		
		linhas.stream().forEach(linha -> {
			Consumo consumo = construirConsumo(linha[0], linha[1], linha[2], empresasMap);
			consumos.add(consumo);
		});

		return consumos;
	}
	
	private Consumo construirConsumo(String mesReferencia, String cnpj, String qtdConsumo, Map<Long, Empresa> empresasMap) {
		
		Consumo consumo = new Consumo();
		ConsumoId consumoId = new ConsumoId();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-mm-yyyy HH:mm:ss");
		LocalDateTime mes = LocalDateTime.parse(mesReferencia, formatador);
		
		consumoId.setEmpresa(empresasMap.get(Long.valueOf(cnpj)));
		consumoId.setMesReferencia(mes);
		consumoId.setQuantidadeConsumo(Long.valueOf(qtdConsumo));
		consumo.setConsumoId(consumoId);
		
		return consumo;
	}

	private HashMap<Long, Cidade> construirMapCidades() {
		HashMap<Long, Cidade> cidadesMap = new HashMap<>();

		final List<Cidade> cidades = cidadeRepo.findAll();

		cidades.stream().forEach(c -> cidadesMap.put(c.getId(), c));

		return cidadesMap;
	}

	private HashMap<Long, Cnae> construirMapCnaes() {
		HashMap<Long, Cnae> cnaesMap = new HashMap<>();

		List<Cnae> cnaes = cnaeRepo.findAll();

		cnaes.stream().forEach(c -> cnaesMap.put(c.getId(), c));

		return cnaesMap;
	}
	
	private HashMap<Long, Empresa> construirMapEmpresas() {
		HashMap<Long, Empresa> empresasMap = new HashMap<>();
		
		List<Empresa> empresas = empresaRepo.findAll();
		
		empresas.stream().forEach(e->empresasMap.put(e.getCnpj(), e));
		
		return empresasMap;
	}

}
