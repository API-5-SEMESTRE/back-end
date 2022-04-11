package api.theVelopers.sas.service;

import java.time.LocalDateTime;
import java.util.List;

import api.theVelopers.sas.entity.Consumo;

public interface ConsumoService {
	
	List<Consumo> salvarTodos(Iterable<Consumo> consumos);
	List<Consumo> salvarTodosFlush(Iterable<Consumo> consumos);
	Long procurarSomaConsumoPorEmpresa(Long cnpj);
	Long procurarSomaConsumoPorEmpresaData(Long cnpj, LocalDateTime data);
	Long procurarSomaConsumoPorVendedor(Long id);
}