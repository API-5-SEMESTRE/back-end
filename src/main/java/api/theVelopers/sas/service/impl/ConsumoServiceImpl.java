package api.theVelopers.sas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.entity.Consumo;
import api.theVelopers.sas.repository.ConsumoRepository;
import api.theVelopers.sas.service.ConsumoService;

@Service
public class ConsumoServiceImpl implements ConsumoService{
	
	@Autowired
	private ConsumoRepository repo;

	@Override
	public List<Consumo> salvarTodos(Iterable<Consumo> consumos) {
		return repo.saveAll(consumos);
	}

	@Override
	public List<Consumo> salvarTodosFlush(Iterable<Consumo> consumos) {
		return repo.saveAllAndFlush(consumos);
	}
	
}
