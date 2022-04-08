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

import api.theVelopers.sas.entity.Consumo;
import api.theVelopers.sas.entity.ConsumoId;
import api.theVelopers.sas.service.CrudService;
import api.theVelopers.sas.service.TransformarDadosService;

@RestController
@RequestMapping(path = {"/consumo"})
public class ConsumoController {

	@Autowired
	private TransformarDadosService transformarDadosService;
	@Autowired
	private CrudService<Consumo, ConsumoId> crud;
	
	@PostMapping("/leitor-csv")
	public ResponseEntity<List<Consumo>> uploadCsv(
			@RequestParam("arquivo") MultipartFile arquivo) {
		final Set<Consumo> consumos = transformarDadosService.transformarDadosConsumo(arquivo);
		
		final List<Consumo> consumosSalvos = crud.salvarTodosFlush(consumos);
		
        return new ResponseEntity<>(consumosSalvos, OK);
	}
}
