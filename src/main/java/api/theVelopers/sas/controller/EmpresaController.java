package api.theVelopers.sas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import api.theVelopers.sas.entity.Empresa;
import api.theVelopers.sas.service.TransformarDadosService;

@RestController
@RequestMapping(path = {"/empresa"})
public class EmpresaController {
	
	@Autowired
	TransformarDadosService transformarDadosService;
	
	@PostMapping("/upload-csv")
	public ResponseEntity<List<Empresa>> uploadCsv(
			@RequestParam("arquivo") MultipartFile arquivo) {
		
		return null;
	}
}
