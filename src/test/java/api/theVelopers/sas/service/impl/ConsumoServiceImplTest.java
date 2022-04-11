package api.theVelopers.sas.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import api.theVelopers.sas.service.ConsumoService;

@SpringBootTest
@Transactional
class ConsumoServiceImplTest {
	
	@Autowired
	private ConsumoService service;

	@Test
	@Rollback
	void deveRetornarSomaDeConsumoCerta() {
		Long soma = service.procurarSomaConsumoPorEmpresa(11924000193l);
		
		assertTrue(soma == 5684);
	}
	
	@Test
	@Rollback
	void deveRetornarSomaDeConsumoCertaPorData() {
		
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");
		LocalDateTime data = LocalDateTime.parse("30/09/2021  00:00:00", formatador);
		
		Long soma = service.procurarSomaConsumoPorEmpresaData(11924000193l, data);
		
		assertTrue(soma == 4440);
	}

}
