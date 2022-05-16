package api.theVelopers.sas.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
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
import api.theVelopers.sas.utils.LocalDateTimeFormatterUtils;

/**
 * 
 * @author jef
 *
 */
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
		
		for(String[] linha: linhas) {
			Empresa novaEmpresa = construirEmpresa(linha[0],linha[1],
													linha[2],linha[3],
													cnaesMap);
			empresas.add(novaEmpresa);
		}

		return empresas;
	}

	private Empresa construirEmpresa(String cnpj, String idCidade, 
										String idCnae, String origem,
										HashMap<Long, Cnae> cnaesMap) {

		Empresa empresa = new Empresa();
		Cidade cidade = cidadeRepo.findById(Long.valueOf(idCidade)).get();
		Cnae cnae = null;
		if(StringUtils.isNotBlank(idCnae)) {
			cnae = cnaesMap.get(Long.valueOf(idCnae));
		}

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
		
		linhas.stream().forEach(linha -> {
			Consumo consumo = construirConsumo(linha[0], linha[1], linha[2]);
			consumos.add(consumo);
		});

		return consumos;
	}
	
	private Consumo construirConsumo(String mesReferencia, String cnpj, String qtdConsumo) {
		
		Consumo consumo = new Consumo();
		ConsumoId consumoId = new ConsumoId();
		LocalDateTime mes = LocalDateTimeFormatterUtils.padronizarLocalDateTime(mesReferencia);
		Empresa empresa = empresaRepo.findById(Long.valueOf(cnpj)).get();
		
		consumoId.setEmpresa(empresa);
		consumoId.setMesReferencia(mes);
		consumoId.setQuantidadeConsumo(Long.valueOf(qtdConsumo));
		consumo.setConsumoId(consumoId);
		
		return consumo;
	}

	private HashMap<Long, Cnae> construirMapCnaes() {
		HashMap<Long, Cnae> cnaesMap = new HashMap<>();

		List<Cnae> cnaes = cnaeRepo.findAll();

		cnaes.stream().forEach(c -> cnaesMap.put(c.getId(), c));

		return cnaesMap;
	}

}
