package api.theVelopers.sas.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Cnae;
import api.theVelopers.sas.exception.TratamentoExcecao;
import api.theVelopers.sas.service.CnaeService;
import api.theVelopers.sas.service.TransformarDadosService;

@RestController
@RequestMapping(path = {"/cnae"})
public class CnaeController extends TratamentoExcecao{
	
	@Autowired
	private TransformarDadosService transformarDadosService;
	@Autowired
	private CnaeService cnaeService;
	
	@PostMapping("/leitor-csv")
	public ResponseEntity<List<Cnae>> uploadCsv(
			@RequestParam("arquivo") MultipartFile arquivo) {
		final Set<Cnae> cnaes = transformarDadosService.transformarDadosCnae(arquivo);
		
		final List<Cnae> cnaesSalvos = cnaeService.salvarTodosFlush(cnaes);
		
        return new ResponseEntity<>(cnaesSalvos, OK);
	}
}
