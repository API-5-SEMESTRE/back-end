package api.theVelopers.sas.controller;

import static org.springframework.http.HttpStatus.OK;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.dto.ConsumoDTO;
import api.theVelopers.sas.entity.Consumo;
import api.theVelopers.sas.service.ConsumoService;
import api.theVelopers.sas.service.TransformarDadosService;

@RestController
@RequestMapping(path = {"/consumo"})
public class ConsumoController {

	@Autowired
	private TransformarDadosService transformarDadosService;
	@Autowired
	private ConsumoService consumoService;
	
	@PostMapping("/leitor-csv")
	public ResponseEntity<List<Consumo>> uploadCsv(
			@RequestParam("arquivo") MultipartFile arquivo) {
		final Set<Consumo> consumos = transformarDadosService.transformarDadosConsumo(arquivo);
		
		final List<Consumo> consumosSalvos = consumoService.salvarTodosFlush(consumos);
		
        return new ResponseEntity<>(consumosSalvos, OK);
	}
	
	@GetMapping("/consumo-total-empresa/{cnpj}")
	public ResponseEntity<Long> pesquisarConsumoTotalPorCnpj(@PathVariable("cnpj")Long cnpj) {
		final Long soma = consumoService.procurarSomaConsumoPorEmpresa(cnpj);
		
		return new ResponseEntity<>(soma, OK);
	}
	
	@GetMapping("/consumo-total-empresa-data/{cnpj}/{data}")
	public ResponseEntity<Long> pesquisarConsumoTotalPorCnpjData(@PathVariable("cnpj")Long cnpj,
																	@PathVariable("data")LocalDateTime data) {
		final Long soma = consumoService.procurarSomaConsumoPorEmpresaData(cnpj, data);
		
		return new ResponseEntity<>(soma, OK);
	}
	
	@GetMapping("/consumo-total-usuario/{id}")
	public ResponseEntity<Long> pesquisarConsumoTotalPorUsuario(@PathVariable("id")Long id) {
		final Long soma = consumoService.procurarSomaConsumoPorVendedor(id);
		
		return new ResponseEntity<>(soma, OK);
	}
	
	@GetMapping("/lista-consumo-empresa/{cnpj}")
	public ResponseEntity<List<ConsumoDTO>> pesquisarConsumosPorCnpj(@PathVariable("cnpj")Long cnpj) {
		final List<ConsumoDTO> consumos = consumoService.procurarConsumosPorCnpj(cnpj);
		
		return new ResponseEntity<>(consumos, OK);
	}
	
	@GetMapping("/lista-consumo-vendedor/{id}")
	public ResponseEntity<List<ConsumoDTO>> pesquisarConsumosPorIdVendedor(@PathVariable("id")Long id) {
		final List<ConsumoDTO> consumos = consumoService.procurarConsumosPorIdVendedor(id);
		
		return new ResponseEntity<>(consumos, OK);
	}
	
}
