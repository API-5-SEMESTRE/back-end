package api.theVelopers.sas.service;

import java.util.List;

import api.theVelopers.sas.entity.Consumo;

public interface ConsumoService {
	
	List<Consumo> salvarTodos(Iterable<Consumo> consumos);
	List<Consumo> salvarTodosFlush(Iterable<Consumo> consumos);
}