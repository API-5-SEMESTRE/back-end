package api.theVelopers.sas.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import api.theVelopers.sas.service.EmpresaService;

@SpringBootTest
class EmpresaServiceImplTest {
	
	@Autowired
	private EmpresaService service;

	@Test
	void test() {
		Map<String, List<String> > map = service.carregarDadosEmpresa("base_empresas");
		
		System.out.println(map.toString());
		
		assertTrue(!map.isEmpty());
	}

}
