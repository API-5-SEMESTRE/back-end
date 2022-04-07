package api.theVelopers.sas.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Cidade;
import api.theVelopers.sas.service.TransformarDadosService;

@RestController
@RequestMapping(path = {"/cidade"})
public class CidadeController {
	
	@Autowired
	TransformarDadosService transformarDadosService;
	
	@PostMapping("/upload-csv")
	public ResponseEntity<Set<Cidade>> uploadCsv(
			@RequestParam("arquivo") MultipartFile arquivo) {
		final Set<Cidade> cidades = transformarDadosService.transformarDadosCidade(arquivo);

        return new ResponseEntity<>(cidades, OK);
	}

}
