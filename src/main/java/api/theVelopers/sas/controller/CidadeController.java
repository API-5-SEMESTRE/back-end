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

import api.theVelopers.sas.entity.Cidade;
import api.theVelopers.sas.exception.TratamentoExcecao;
import api.theVelopers.sas.service.CidadeService;
import api.theVelopers.sas.service.TransformarDadosService;

@RestController
@RequestMapping(path = {"/cidade"})
public class CidadeController extends TratamentoExcecao{
	
	@Autowired
	private TransformarDadosService transformarDadosService;
	@Autowired
	private CidadeService cidadeService;
	
	@PostMapping("/leitor-csv")
	public ResponseEntity<List<Cidade>> uploadCsv(
			@RequestParam("arquivo") MultipartFile arquivo) {
		final Set<Cidade> cidades = transformarDadosService.transformarDadosCidade(arquivo);
		
		final List<Cidade> cidadesSalvas = cidadeService.salvarTodosFlush(cidades);
		
        return new ResponseEntity<>(cidadesSalvas, OK);
	}

}
