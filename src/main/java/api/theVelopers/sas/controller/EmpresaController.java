package api.theVelopers.sas.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.dto.EmpresaDTO;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.entity.EmpresaScore;
import api.theVelopers.sas.exception.TratamentoExcecao;
import api.theVelopers.sas.service.EmpresaScoreService;
import api.theVelopers.sas.service.EmpresaService;
import api.theVelopers.sas.service.TransformarDadosService;

/**
 * 
 * @author jef
 *
 */
@RestController
@RequestMapping(path = {"/empresa"})
public class EmpresaController extends TratamentoExcecao{
	
	@Autowired
	private TransformarDadosService transformarDadosService;
	@Autowired
	private EmpresaService empresaService;
	@Autowired
	private EmpresaScoreService scoreService;
	
	@PostMapping("/leitor-csv")
	@ResponseStatus(CREATED)
	public List<Empresa> uploadCsv(
			@RequestParam("arquivo") MultipartFile arquivo) {
		final Set<Empresa> empresas = transformarDadosService.transformarDadosEmpresa(arquivo);
		final List<Empresa> empresasSalvas = empresaService.salvarTodosFlush(empresas);
        return empresasSalvas;
	}
	
	@GetMapping("/todas-empresas")
	@ResponseStatus(OK)
	public List<Long> pesquisarTodasEmpresas() {
		return empresaService.findAllCnpj();
	}
	
	@GetMapping("/todas-empresas/{pagina}/{tamanho}")
	@ResponseStatus(OK)
	public Page<Empresa> pesquisarTodasEmpresasPaginacao(@PathVariable int pagina, @PathVariable int tamanho) {
		return empresaService.todasEmpresas(pagina, tamanho);
	}
	
	@GetMapping("/pesquisar-empresa/{cnpj}")
	@ResponseStatus(OK)
	public EmpresaDTO pesquisarEmpresaPorCnpj(@PathVariable("cnpj")Long cnpj) {
		return empresaService.procurarPorCnpj(cnpj);
	}
	
	@GetMapping("/pesquisar-score-por-regiao/{regiao}/{pagina}/{tamanho}/{sort}")
	@ResponseStatus(OK)
	public ResponseEntity<Page<EmpresaScore>> pesquisarScorePorRegiao(@PathVariable("regiao")String regiao,
																		@PathVariable("pagina")int pagina,
																		@PathVariable("tamanho")int tamanho,
																		@PathVariable("sort")int sort) {
		final Page<EmpresaScore> scores = scoreService.procurarPorRegiao(regiao, pagina, tamanho, sort);
		
		return new ResponseEntity<>(scores, OK);
	}
	
	@GetMapping("/pesquisar-score-por-origem/{origem}/{pagina}/{tamanho}/{sort}")
	@ResponseStatus(OK)
	public Page<EmpresaScore> pesquisarScorePorOrigem(@PathVariable("origem")String origem,
																		@PathVariable("pagina")int pagina,
																		@PathVariable("tamanho")int tamanho,
																		@PathVariable("sort")int sort) {
		return scoreService.procurarPorOrigem(origem, pagina, tamanho, sort);
	}
	
	@GetMapping("/pesquisar-score/{cnpj}")
	@ResponseStatus(OK)
	public EmpresaScore pesquisarScorePorCnpj(@PathVariable("cnpj")Long cnpj) {
		return scoreService.procurarPorCnpj(cnpj);
	}
}
