package api.theVelopers.sas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.theVelopers.sas.entity.Cidade;
import api.theVelopers.sas.repository.CidadeRepository;
import api.theVelopers.sas.service.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	@Override
	public List<Cidade> salvarTodos(Iterable<Cidade> cidades) {
		return repo.saveAll(cidades);
	}

	@Override
	public List<Cidade> salvarTodosFlush(Iterable<Cidade> cidades) {
		return repo.saveAllAndFlush(cidades);
	}

}
