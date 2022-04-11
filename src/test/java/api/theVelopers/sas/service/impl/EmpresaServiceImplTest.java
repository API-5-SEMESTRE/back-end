package api.theVelopers.sas.service.impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertTrue;

import api.theVelopers.sas.service.EmpresaService;

@SpringBootTest
@Transactional
class EmpresaServiceImplTest {
	
	@Autowired
	private EmpresaService service;

	@Test
	void deveRetornarListaDeTodosCNPJ() {
		List<Long> cnpjs = service.findAllCnpj();
		
		assertTrue(!cnpjs.isEmpty());
	}

}
