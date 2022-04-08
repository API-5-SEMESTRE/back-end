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

import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.service.CrudService;
import api.theVelopers.sas.service.TransformarDadosService;

@RestController
@RequestMapping(path = {"/empresa"})
public class EmpresaController {
	
	@Autowired
	private TransformarDadosService transformarDadosService;
	@Autowired
	private CrudService<Empresa, Long> crud;
	
	@PostMapping("/leitor-csv")
	public ResponseEntity<List<Empresa>> uploadCsv(
			@RequestParam("arquivo") MultipartFile arquivo) {
		final Set<Empresa> empresas = transformarDadosService.transformarDadosEmpresa(arquivo);
		final List<Empresa> empresasSalvas = crud.salvarTodosFlush(empresas);
        return new ResponseEntity<>(empresasSalvas, OK);
	}
	
	@GetMapping("/todos-usuarios")
	public ResponseEntity<List<Empresa>> pesquisarTodasEmpresas() {
		final List<Empresa> empresas = crud.procurarTodos();
		
		return new ResponseEntity<>(empresas, OK);
	}
}
