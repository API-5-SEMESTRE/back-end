package api.theVelopers.sas.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.dto.EmpresaDTO;
import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.service.EmpresaService;
import api.theVelopers.sas.service.TransformarDadosService;

@RestController
@RequestMapping(path = {"/empresa"})
public class EmpresaController {
	
	@Autowired
	private TransformarDadosService transformarDadosService;
	@Autowired
	private EmpresaService empresaService;
	
	@PostMapping("/leitor-csv")
	public ResponseEntity<List<Empresa>> uploadCsv(
			@RequestParam("arquivo") MultipartFile arquivo) {
		final Set<Empresa> empresas = transformarDadosService.transformarDadosEmpresa(arquivo);
		final List<Empresa> empresasSalvas = empresaService.salvarTodosFlush(empresas);
        return new ResponseEntity<>(empresasSalvas, OK);
	}
	
	@GetMapping("/todas-empresas")
	public ResponseEntity<List<EmpresaDTO>> pesquisarTodasEmpresas() {
		final List<EmpresaDTO> empresas = empresaService.procurarTodos();
		
		return new ResponseEntity<>(empresas, OK);
	}
}
