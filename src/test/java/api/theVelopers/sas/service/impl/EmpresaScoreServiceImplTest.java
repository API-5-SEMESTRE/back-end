package api.theVelopers.sas.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import api.theVelopers.sas.entity.EmpresaScore;
import api.theVelopers.sas.service.EmpresaScoreService;

@SpringBootTest
@Transactional
class EmpresaScoreServiceImplTest {
	
	@Autowired
	private EmpresaScoreService service;
	
	@Test
	@Rollback
	void deveFuncionarCnaeNull() {
		
		Page<EmpresaScore> scores = service.procurarPorFiltroCompleto("NORTE", "SPC", null, "AC", 1, 10, 0);
		
		assertTrue(scores.get().count() > 0L);
	}
	
	@Test
	@Rollback
	void deveFuncionarOrigemNull() {
		
		Page<EmpresaScore> scores = service.procurarPorFiltroCompleto("NORTE", null, "FABRICAÇÃO", "AC", 1, 10, 0);
		
		assertTrue(scores.get().count() > 0L);
	}
	
	@Test
	@Rollback
	void deveFuncionarEstadoNull() {
		
		Page<EmpresaScore> scores = service.procurarPorFiltroCompleto("NORTE", "SPC", "FABRICAÇÃO", null, 1, 10, 0);
		
		assertTrue(scores.get().count() > 0L);
	}
	
	@Test
	@Rollback
	void deveFuncionarOrigemEstadoNull() {
		
		Page<EmpresaScore> scores = service.procurarPorFiltroCompleto("NORTE", null, "FABRICAÇÃO", null, 1, 10, 0);
		
		assertTrue(scores.get().count() > 0L);
	}
	
	@Test
	@Rollback
	void deveFuncionarOrigemCnaeNull() {
		
		Page<EmpresaScore> scores = service.procurarPorFiltroCompleto("NORTE", null, null, "AC", 1, 10, 0);
		
		assertTrue(scores.get().count() > 0L);
	}

}
